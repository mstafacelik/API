package com.API.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetRequest06 {

    @Test
    public void test01() {


   /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
    */


        String uri="https://jsonplaceholder.typicode.com/todos/123";

        Response response=given().
                accept("application/json").
                when().
                get(uri);

        response.prettyPrint();


        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).body("userId", equalTo(7),
                "title",equalTo("esse et quis iste est earum aut impedit"),
                "completed", equalTo(false), response.header("Server"),equalTo("cloudflare"));














    }


}
