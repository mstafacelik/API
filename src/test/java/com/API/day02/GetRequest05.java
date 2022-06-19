package com.API.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest05 {

    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
   ve employees sayisinin 24
   ve employee'lerden birinin "Ashton Cox"
   ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
     */


    @Test
    public void test() {

        String uri = " http://dummy.restapiexample.com/api/v1/employees";

        Response response = given()
                .accept("application/json")
                .when()
                .get(uri);

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"),
                        "data.employee_age", hasItems(21, 23, 61));


    }


}
