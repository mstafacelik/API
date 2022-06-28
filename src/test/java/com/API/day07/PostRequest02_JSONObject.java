package com.API.day07;

import com.API.testBase.HerOkuAppTestBase;
import com.API.testData.HerOkuTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest02_JSONObject extends HerOkuAppTestBase {

     /*
    https://restful-booker.herokuapp.com/booking
    { "firstname": "mustingo",
               "lastname": "celik",
               "totalprice": 5000,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2022-10-10",
                   "checkout": "2022-10-15"
                }
 }
 gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " mustingo ",
         "lastname": " celik ",
         "totalprice":  5000,
         "depositpaid": true,
         "bookingdates": {
              "checkin": "2022-10-10",
              "checkout": "2022-10-15"
         },
        }
olduğunu test edin
     */

    @Test
    public void test() {

        // 1-uri
        spec02.pathParam("parametre1", "booking");

        // 2-request Body ve expected data ayni oldugundan tek bir JSONObject kullanilmasi yeterli
        HerOkuTestData herOkuTestData = new HerOkuTestData();

        // NOT: JSONObject ile expected datalari olusturuyoruz. Onceki classlarda expected datalari
        //      HashMap e atiyorduk. Bu durumda assert ederken casting yapmak zorundayiz.
        //      JSONObject'te expected datanin assert'i icin casting islemine gerek kalmiyor
        JSONObject requestAndExpectedData = herOkuTestData.setUpTestAndRequestData();

        System.out.println("requestAndExpectedData = " + requestAndExpectedData);

        // 3- request gonder
        Response response = given().
                contentType(ContentType.JSON).
                when().
                spec(spec02).
                auth().basic("admin", "password123").
                body(requestAndExpectedData.toString()).// // toString yazmayi unutma!!!, JSONObject icin gecerli. Map lerde bu kural yok
                        post("/{parametre1}");

        response.prettyPrint();

        // 4- actual data olustur ve karsilastir

        // ---> DE-Serialization ile

        // NOT: Response'u bir JSONObject e assign edemeyiz, bir Map'e assign etmemiz gerek.
        // Oysa expectedData lari bir JSONObject e assign  edebiliriz.


        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(requestAndExpectedData.getString("firstname"),
                ((Map) actualDataMap.get("booking")).get("firstname"));

        assertEquals(requestAndExpectedData.getString("lastname"),
                ((Map) actualDataMap.get("booking")).get("lastname"));

        assertEquals(requestAndExpectedData.getInt("totalprice"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("totalprice"));

        assertEquals(requestAndExpectedData.getBoolean("depositpaid"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("depositpaid"));


        // expected Data yi JSONObject ile getirdik
        assertEquals(requestAndExpectedData.getJSONObject("bookingdates").getString("checkin"),
                ((Map) ((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        assertEquals(requestAndExpectedData.getJSONObject("bookingdates").getString("checkout"),
                ((Map) ((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));


        // ---> JsonPath ile

        JsonPath jsonPath = response.jsonPath();

        assertEquals(requestAndExpectedData.getString("firstname"), jsonPath.getString("booking.firstname"));
        assertEquals(requestAndExpectedData.getString("lastname"), jsonPath.getString("booking.lastname"));
        assertEquals(requestAndExpectedData.getInt("totalprice"), jsonPath.getInt("booking.totalprice"));
        assertEquals(requestAndExpectedData.getBoolean("depositpaid"), jsonPath.getBoolean("booking.depositpaid"));

        // expected Data yi JSONObject ile getirdik
        assertEquals(requestAndExpectedData.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getString("booking.bookingdates.checkin"));

        assertEquals(requestAndExpectedData.getJSONObject("bookingdates").getString("checkout"),
                jsonPath.getString("booking.bookingdates.checkout"));


    }


}
