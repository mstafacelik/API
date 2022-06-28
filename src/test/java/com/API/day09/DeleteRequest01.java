package com.API.day09;

import com.API.testBase.DummyTestBase;
import com.API.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class DeleteRequest01 extends DummyTestBase {

     /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
     */

    @Test
    public void test() {

        //uri
        spec03.pathParams("parametre1", "delete", "parametre2", 2);

        // expectedData
        DummyTestData dummyTestData = new DummyTestData();

        JSONObject expectedData = dummyTestData.deleteExpectedData();
        System.out.println("expectedData = " + expectedData);

        //Request gonder

        Response response = given().
                contentType(ContentType.JSON).
                auth().
                basic("admin", "password123").
                when().
                spec(spec03).
                delete("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // actual data olustur ve dogrula

        //--> JsonPath ile
        JsonPath jsonPath = response.jsonPath();

      //  assertEquals(expectedData.getInt("DatA"), jsonPath.getInt("data"));
        assertEquals(expectedData.getString("StatUs"), jsonPath.getString("status"));
        assertEquals(expectedData.getString("MesSagE"), jsonPath.getString("message"));
        assertEquals(expectedData.getInt("StatusCoDe"), response.getStatusCode());

        //--> De-Serialization ile
        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.getInt("DatA"), actualData.get("DatA"));
        assertEquals(expectedData.getString("StatuS"), actualData.get("status"));
        assertEquals(expectedData.getString("MesSagE"), actualData.get("message"));
        assertEquals(expectedData.getInt("StatusCoDe"), response.getStatusCode());

        //--> Matchers ile

        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(expectedData.getInt("StatusCoDe")).
                body("data", equalTo(expectedData.get("DatA")),
                        "status", equalTo(expectedData.getString("StatuS")),
                        "message", equalTo(expectedData.getString("MesSagE")));


    }


}
