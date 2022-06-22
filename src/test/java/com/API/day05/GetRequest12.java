package com.API.day05;

import com.API.testBase.HerOkuAppTestBase;
import com.API.testData.HerOkuTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest12 extends HerOkuAppTestBase {

    /*
    https://restful-booker.herokuapp.com/booking/4 url ine bir istek gönderildiğinde
 dönen response body nin
{
    "firstname": "Richard",
    "lastname": "Roe",
    "totalprice": 150,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-09-25",
        "checkout": "2018-09-30"
    },
    "additionalneeds": "Breakfast"
 } gibi olduğunu test edin
     */

    @Test
    public void test01() {

        // 1. uri olustur
        spec02.pathParams("parametre1", "booking",
                "parametre2", 4);


        // 2. expected Data olustur
        HerOkuTestData herOkuTestData = new HerOkuTestData();

        HashMap<String, Object> expectedDataMap = herOkuTestData.setUpData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // 3. request gönder

        Response response = given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // 4.  Actual Data olustur ve Dogrula --> 1.Yöntem DE-Serialization

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
        // response dan gelen datayi map gibi(as) alip, actual dataya atadik


        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        Assert.assertEquals(   ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map) actualDataMap.get("bookingdates")).get("checkin"));

        Assert.assertEquals(   ((Map) expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map) actualDataMap.get("bookingdates")).get("checkout")  );


        // --> 2. Yöntem JsonPath

        JsonPath jsonPath = response.jsonPath();
        // JsonPath te child lara nokta koyup ulasabiliyoruz. ÖRN : data[2].employee_name

        assertEquals(expectedDataMap.get("firstname"), jsonPath.getString("firstname"));
        assertEquals(expectedDataMap.get("lastname"), jsonPath.getString("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), jsonPath.getInt("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), jsonPath.getBoolean("depositpaid"));

        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));
        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"),
                jsonPath.getString("bookingdates.checkout"));


    }
}
