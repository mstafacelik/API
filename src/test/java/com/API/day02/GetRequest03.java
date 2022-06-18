package com.API.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest03 {

    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in "Susan"
    ve lastname'in "Jones"
    ve checkin date'in 2018-10-07"
    ve checkout date'in 2020-09-30 oldugunu test edin
            */

    @Test
    public void test01() {


        String uri = "https://restful-booker.herokuapp.com/booking/7";


        Response response = given().
                accept("application/json").
                when().
                get(uri);

        response.prettyPrint();


        // erste Lösung

     // response.then().
     //         assertThat().
     //         statusCode(200).
     //         contentType(ContentType.JSON).
     //         body("firstname", equalTo("Jim")).
     //         body("lastname", equalTo("Ericsson")).
     //         body("totalprice", equalTo(668)).
     //         body("depositpaid", equalTo(false)).
     //         body("bookingdates.checkin", equalTo("2017-07-03"))
     //         .body("bookingdates.checkout", equalTo("2022-02-14"));


        // zweite Lösung

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Jim"),
                        "lastname", equalTo("Ericsson"),
                        "totalprice", equalTo(668), "depositpaid",equalTo(false),
                        "bookingdates.checkin", equalTo("2017-07-03"),
                        "bookingdates.checkout", equalTo("2022-02-14"));


    }


}
