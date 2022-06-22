package com.API.day05;

import com.API.testBase.HerOkuAppTestBase;
import com.API.testData.HerOkuTestData;
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
    public void test01(){

        // 1. uri olustur
        spec02.pathParams("parametre1","booking",
                "parametre2",4);


        // 2. expected Data olustur
        HerOkuTestData herOkuTestData=new HerOkuTestData();

        HashMap<String,Object> expectedDataMap= herOkuTestData.setUpData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // 3. request gönder

        Response response=given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // 4. actual Data olustur--> DE-Serialization

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
        // response dan gelen datayi map gibi(as) alip, actual dataya atadik

        // 5. dogrulama yap

        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(  ((Map)(expectedDataMap.get("bookingdates")) ).get("checkin"),
                    (    (Map)actualDataMap.get("bookingdates")).get("checkin")     );

        assertEquals(  ((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                    (    (Map)actualDataMap.get("bookingdates")).get("checkout")    );













    }


}
