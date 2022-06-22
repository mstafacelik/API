package com.API.testData;

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
}
