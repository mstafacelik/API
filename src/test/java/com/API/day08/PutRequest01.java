package com.API.day08;

import com.API.testBase.JsonPlaceHolderTestBase;
import com.API.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PutRequest01 extends JsonPlaceHolderTestBase {

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
 {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false
 }
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
 }
     */


    @Test
    public void test(){

        spec01.pathParams("parametre1","todos","parametre2","198");



        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();
        JSONObject requestBodyAndExpectedData=jsonPlaceHolderTestData.setUpPutData();
        System.out.println("requestBodyAndExpectedData = " + requestBodyAndExpectedData);

        Response response=given().
                contentType(ContentType.JSON).
                auth().basic("admin","password123").
                when().
                spec(spec01).
                body(requestBodyAndExpectedData.toString()). // // toString yazmayi unutma!!!
                put("/{parametre1}/{parametre2}");

        response.prettyPrint();


        JsonPath jsonPath=response.jsonPath();

        assertEquals(requestBodyAndExpectedData.getInt("statusCode"),response.getStatusCode());
        assertEquals(requestBodyAndExpectedData.getBoolean("completed"),jsonPath.getBoolean("completed"));
        assertEquals(requestBodyAndExpectedData.getString("title"),jsonPath.getString("title"));
        assertEquals(requestBodyAndExpectedData.getInt("userId"),jsonPath.getInt("userId"));


    }

}
