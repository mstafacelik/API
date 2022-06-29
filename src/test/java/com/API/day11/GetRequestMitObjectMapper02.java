package com.API.day11;

import com.API.testBase.HerOkuAppTestBase;
import com.API.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestMitObjectMapper02 extends HerOkuAppTestBase {

     /*
    GetRequestWithObjectMapper02:
  https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
 status kodun 200 ve response body’nin
{
"FirstnaMe": "Jim",
"laStnamE": "Brown",
"TotalpricE": 629,
"dEpositpaiD": true,
"booKingdaTes": {
"cheCkiN": "2021-01-11",
"checKouT": "2021-06-02"
},
"additionalneeds": "Breakfast"
}
Olduğunu Object Mapper kullanarak test edin

     */


    @Test
    public void test() {

        //1- uri
        spec02.pathParams("parametre1", "booking", "parametre2", 2);

        //2- expected data
        String jsonData = "{\n" +
                "\"FirstnaMe\": \"Jim\",\n" +
                "\"laStnamE\": \"Brown\",\n" +
                "\"TotalpricE\": 629,\n" +
                "\"dEpositpaiD\": true,\n" +
                "\"booKingdaTes\": {\n" +
                "\"cheCkiN\": \"2021-01-11\",\n" +
                "\"checKouT\": \"2021-06-02\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";

        Map<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(jsonData, Map.class);
        System.out.println("expectedData = " + expectedDataMap);

        //3- request gonder
        Response response = given().
                contentType(ContentType.JSON).
                when().
                spec(spec02).
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();


        //4- actual data olustur ve dogrula
        // DE-Serialization

        Map<String, Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(), Map.class); // asString() 'i unutma!
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(expectedDataMap.get("FirstnaMe"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("laStnamE"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("TotalpricE"), actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("dEpositpaiD"), actualDataMap.get("depositpaid"));

        assertEquals(((Map) expectedDataMap.get("booKingdaTes")).get("cheCkiN"),
                ((Map) actualDataMap.get("bookingdates")).get("checkin"));

        assertEquals(((Map) expectedDataMap.get("booKingdaTes")).get("checKouT"),
                ((Map) actualDataMap.get("bookingdates")).get("checkout"));


    }


}
