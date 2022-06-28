package com.API.day09;

import com.API.pojos.TodosPojo;
import com.API.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert.*;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestMitPojo01 extends JsonPlaceHolderTestBase {
     /*
    https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
 Request body{
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false
}
 Status kodun 201, response body ‘nin ise
{
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false
 }
     */

    @Test
    public void test() {


        //uri
        spec01.pathParam("parametre1", "todos");

        // request body expected data ayni

        TodosPojo requestAndExpectedData = new TodosPojo(21, 201, "Tidy your room", false);


        System.out.println("requestAndExpectedData = " + requestAndExpectedData);

        // request gonder

        Response response = given().
                contentType(ContentType.JSON).
                auth().basic("admin", "password123").
                when().
                spec(spec01).
                body(requestAndExpectedData). // toString gerek yok JSONObject in aksine. Zaten burasi toString olarak geldi
                        post("/{parametre1}");

        response.prettyPrint();


        // actual data olustur ve dogrula


        TodosPojo actualData = response.as(TodosPojo.class); // DE-Serialization
        // HashMap yerine Pojo ya assign ettik.


        assertEquals(200, response.getStatusCode());
        assertEquals(requestAndExpectedData.getUserId(), actualData.getUserId());
        assertEquals(requestAndExpectedData.getId(), actualData.getId());
        assertEquals(requestAndExpectedData.getTitle(), actualData.getTitle());
        assertEquals(requestAndExpectedData.isCompleted(), actualData.isCompleted());


    }


}
