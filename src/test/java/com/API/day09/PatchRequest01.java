package com.API.day09;

import com.API.testBase.JsonPlaceHolderTestBase;
import com.API.testData.JsonPlaceHolderTestData;
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

public class PatchRequest01 extends JsonPlaceHolderTestBase {

     /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {
      "title": "API calismaliyim"
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
     */

    @Test
    public void test() {


        // uri
        spec01.pathParams("parametre1", "todos", "parametre2", "198");

        // request body
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();

        JSONObject requestBody = jsonPlaceHolderTestData.setUpPatchRequestData();
        System.out.println("requestBody = " + requestBody);


        // expected data
        JSONObject expectedData = jsonPlaceHolderTestData.setUpPatchExpectedData();
        System.out.println("expectedData = " + expectedData);


        //response data
        Response response = given().
                contentType(ContentType.JSON).
                auth().
                basic("admin", "password123").
                when().
                spec(spec01).
                body(requestBody.toString()).          // toString YAZMAYI UNUTMA!!!
                        patch("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // actual data olustur ve Dogrula

        // --> DE-Serialization ile
        HashMap<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.getInt("statusCode"), response.getStatusCode());
        assertEquals(expectedData.getInt("userId"), actualData.get("userId"));
        assertEquals(expectedData.getBoolean("completed"), actualData.get("completed"));
        assertEquals(expectedData.getString("title"), actualData.get("title"));

        // --> JsonPath ile

        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedData.getInt("statusCode"), response.getStatusCode());
        assertEquals(expectedData.getInt("userId"), jsonPath.getInt("userId"));
        assertEquals(expectedData.getBoolean("completed"), jsonPath.getBoolean("completed"));
        assertEquals(expectedData.getString("title"), jsonPath.getString("title"));


        // --> Matchers ile

        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                body("userId", equalTo(expectedData.getInt("userId")),
                        "completed", equalTo(expectedData.getBoolean("completed")),
                        "title", equalTo(expectedData.getString("title")));


    }
}