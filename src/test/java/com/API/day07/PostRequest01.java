package com.API.day07;

import com.API.testBase.DummyTestBase;
import com.API.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest01 extends DummyTestBase {

      /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
 "name":"Ahmet Aksoy",
 "salary":"1000",
 "age":"18",
  }
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
{
 "status": "success",
 "data": {
 “id”:…
 },
 "message": "Successfully! Record has been added."
 }
olduğunu test edin
     */

    @Test
    public void test() {


        // 1. uri olustur

        spec03.pathParam("parametre1", "create");


        // 2- request body  olustur

        DummyTestData dummyTestData = new DummyTestData();
        // post request yaparken body gondermek zorundayiz
        // test data class inda olusturdumuz request body i burada kullaniyoruz
        HashMap<String, String> requestBodyMap = dummyTestData.setUpRequestBody();


        // 3- expected data olustur

        HashMap<String, Object> expectedDataMap = dummyTestData.setUpExpectedTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);


        // 4- request gonder

        Response response=given().accept(ContentType.JSON).
                when().
                spec(spec03).auth().basic("admin","password123").
                body(requestBodyMap).
                post("/{parametre1}");

        response.prettyPrint();


        // 5- actual data olustur ve dogrula

        // -->DE-Serialization ile

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("StatuS"),actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("MessagE"),actualDataMap.get("message"));

        // -->JsonPath ile

        JsonPath jsonPath=response.jsonPath();

        assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("StatuS"),jsonPath.get("status"));
        assertEquals(expectedDataMap.get("MessagE"),jsonPath.get("message"));




    }


}
