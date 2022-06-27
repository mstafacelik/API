package com.API.testData;

import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuTestData {

     /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
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


    public HashMap<String, Object> setUpData() {

        HashMap<String, Object> bookingdatesMap = new HashMap<>(); //icteki map olusturuldu
        bookingdatesMap.put("checkin", "2018-09-25");
        bookingdatesMap.put("checkout", "2018-09-30");


        HashMap<String, Object> expectedData = new HashMap<>(); //distaki map olusturuldu
        expectedData.put("firstname", "Richard");
        expectedData.put("lastname", "Roe");
        expectedData.put("totalprice", 150);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap); //icteki mapi, bookingdates'in value'su olarak yazdik

        return expectedData;


    }


    public JSONObject setUpTestAndRequestData() {



        // JSONObject class ini kullanarak expected data lari olusturuyoruz, Map ile olusturmak yerine.
        // Bu sayede Casting yapmaya gerek kalmiyor Assertion larda.

        JSONObject bookingdates = new JSONObject(); //icteki JSONObject olusturuldu

        bookingdates.put("checkin", "2022-10-10");
        bookingdates.put("checkout", "2022-10-15");


        // request body ve expected data ayni oldugu icin ayri ayri olusturmuyoruz
        // bir tanesini olusturup ikisi icin de kullanacagiz

        JSONObject requestAndExpectedData = new JSONObject(); //distaki JSONObject olusturuldu

        requestAndExpectedData.put("firstname", "mustafa");
        requestAndExpectedData.put("Lastname", "celik");
        requestAndExpectedData.put("totalprice", 5000);
        requestAndExpectedData.put("depositpaid", true);
        requestAndExpectedData.put("bookingdates", bookingdates);

        return requestAndExpectedData;


    }


}
