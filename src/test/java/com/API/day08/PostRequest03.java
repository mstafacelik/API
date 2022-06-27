package com.API.day08;

import com.API.testBase.JsonPlaceHolderTestBase;
import com.API.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PostRequest03 extends JsonPlaceHolderTestBase {

     /*
   https://jsonplaceholder.typicode.com/todos uri ye asagidaki
    {
               "userID": 55,
               "title ": "tidy your room",
               "completed": false,
 }
 gönderildiğinde, Status kodun 201 olduğunu ve dönen response body nin ,
  {
                "userID": 55,
               "title ": "tidy your room",
               "completed": false,
               "id":-
        }
olduğunu test edin
     */

    @Test
    public void test() {

        // uri
        spec01.pathParam("parametre1", "todos");

        // request body ve expected data (datalar ayni)
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();

        JSONObject requestBodyAndExpectedData = jsonPlaceHolderTestData.setUpRequestBodyAndExpectedData();

        System.out.println("requestBodyAndExpectedData = " + requestBodyAndExpectedData);

        // request gonder

        Response response = given().
                contentType(ContentType.JSON)
                .when().
                spec(spec01).
                auth().basic("admin", "password").
                body(requestBodyAndExpectedData.toString()). // toString yazmayi unutma!!!
                        post("/{parametre1}");

        response.prettyPrint();


        // actual data olustur ve dogrula

        //---> DE-Serialization ile

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);


        assertEquals(requestBodyAndExpectedData.getInt("statusCode"), response.getStatusCode());
        assertEquals(requestBodyAndExpectedData.getInt("UserId"), actualDataMap.get("UserId"));
        assertEquals(requestBodyAndExpectedData.getString("title"), actualDataMap.get("title"));
        assertEquals(requestBodyAndExpectedData.getBoolean("completed"), actualDataMap.get("completed"));

        // ---> JsonPath ile

        JsonPath jsonPath = response.jsonPath();

        assertEquals(requestBodyAndExpectedData.getInt("statusCode"), response.getStatusCode());
        assertEquals(requestBodyAndExpectedData.getInt("UserId"), jsonPath.getInt("UserId"));
        assertEquals(requestBodyAndExpectedData.getString("title"), jsonPath.getString("title"));
        assertEquals(requestBodyAndExpectedData.getBoolean("completed"), jsonPath.getBoolean("completed"));

        // ---> Matchers ile

        response.
                then().
                assertThat().
                statusCode(requestBodyAndExpectedData.getInt("statusCode")).
                body("UserId", equalTo(requestBodyAndExpectedData.getInt("UserId")),
                        "completed", equalTo(requestBodyAndExpectedData.getBoolean("completed")),
                        "title", equalTo(requestBodyAndExpectedData.getString("title")));


    }


}
