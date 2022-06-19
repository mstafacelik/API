package com.API.day02;

import com.API.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetRequest06 extends JsonPlaceHolderTestBase {

    @Test
    public void test01() {


   /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare", "Connection" in "keep-alive"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
    */



    //    String uri="https://jsonplaceholder.typicode.com/todos/123";

        spec01.pathParams("parametre1","todos",
                "parametre2",123);



        Response response=given().
                accept("application/json"). // Eger API sadece json tipini destekliyorsa bunu yazmaya gerek yok.
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();


        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                headers("Server", equalTo("cloudflare"),
                        "Connection",equalTo("keep-alive")).
                body("userId", equalTo(7),
                "title",equalTo("esse et quis iste est earum aut impedit"),
                "completed", equalTo(false));














    }


}
