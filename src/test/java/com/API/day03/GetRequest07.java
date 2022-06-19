package com.API.day03;

import com.API.testBase.HerOkuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest07 extends HerOkuAppTestBase {


    /*
    https://restful-booker.herokuapp.com/booking/2781 url’ine bir request yolladigimda
    HTTP Status Code’unun 200
    ve response content type’inin “application/JSON” oldugunu
    ve response body’sinin asagidaki gibi oldugunu test edin
    {
    "firstname": "Ashley",
    "lastname": "Garcia",
    "totalprice": 240,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-07-23",
        "checkout": "2022-07-28"
    },
    "additionalneeds": "late checkout"

    }
*/


    @Test
    public void test01() {


        spec02.pathParams("parametre1", "booking",
                "parametre2", 2);

        Response response = given().
                accept("application/JSON").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();


        // 1. Yöntem ================== Matcher(s) Class ile Dogrulama=====================

        /*   response.
                   then().
                   assertThat().
                   statusCode(200).
                   contentType("application/JSON").
                   body("firstname", equalTo("Ashley"),
                           "lastname", equalTo("Garcia"),
                           "totalprice", equalTo(240),
                           "depositpaid", equalTo(false),
                           "bookingsdates.checkin", equalTo("2022-07-23"),
                           "bookingsdates.checkout", equalTo("2022-07-28"));

         */


        //2. Yöntem ================== JsonPath ile Dogrulama=====================

/*
        - Matcher(s) Class ile JsonPath i ayni anda da kullanilabilir dogrulama icin.
        - Eger statusCode veya contentType dogrulamamiz gerekiyorsa Matcher(s) Class ile
        - ya da Junit Assert ile zaten dogrulama yapmak zorundayiz.
        - Cunku jsonPath ile sadece response body'ye ait bilgiler gelir.
        - StatusCode ile contentType response body de yer almaz.

 */


        // response dan gelen body'yi jsonPath nesnesine assign ederiz
        // jsonPath in icerisinde su an sadece response body bilgileri var

        JsonPath jsonPath = response.jsonPath();


        // statusCode ve contentType'i Matcher(s) ile dogrulama
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/JSON");

        // Eger statusCode ve contentType'i Matcher(s) ile dogrulama yapmak istemezsek su sekilde yapariz

        assertEquals(200,response.getStatusCode());
        assertEquals("application/JSON", response.getContentType());


        assertEquals("Ashley", jsonPath.get("firstname"));
        assertEquals("Garcia", jsonPath.get("lastname"));
        assertEquals(240, jsonPath.getInt("totalprice"));
        assertEquals(false, jsonPath.getBoolean("depositpaid"));
        assertEquals("2022-07-23", jsonPath.get("bookingdates.checkin"));
        assertEquals("2022-07-28", jsonPath.get("bookingdates.checkout"));
        assertEquals("late checkout", jsonPath.get("additionalneeds"));


    }


}
