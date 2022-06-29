package com.API.day11;

import com.API.testBase.JsonPlaceHolderTestBase;
import com.API.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestMitObjectMapper01 extends JsonPlaceHolderTestBase {

/*

 https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
 {
 "UserID": 10,
 "Id": 198,
 "TitlE": "quis eius est sint explicabo",
 "CompleteT": true
 }
Olduğunu Object Mapper kullanarak test edin
     */

    @Test
    public void test() {


        //1- uri
        spec01.pathParams("parametre1", "todos", "parametre2", 198);

        //2- expected data
        String jsonData = "{\n" +
                " \"UserID\": 10,\n" +
                " \"Id\": 198,\n" +
                " \"TitlE\": \"quis eius est sint explicabo\",\n" +
                " \"CompleteT\": true\n" +
                " }";

        Map<String, Object> expectedDAtaMap = JsonUtil.convertJsonToJava(jsonData, Map.class);

        System.out.println("expectedDAta = " + expectedDAtaMap);

        //3- request gonder
        Response response = given().
                contentType(ContentType.JSON).
                when().
                spec(spec01).
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();


        //4- actual data olustur ve dogrula
        // DE-Serialization
        Map<String, Object> actualDataMAp = JsonUtil.convertJsonToJava(response.asString(), Map.class); // asString() 'i unutma!
        System.out.println("actualData = " + actualDataMAp);

        assertEquals(expectedDAtaMap.get("UserID"),actualDataMAp.get("userId"));
        assertEquals(expectedDAtaMap.get("Id"),actualDataMAp.get("id"));
        assertEquals(expectedDAtaMap.get("TitlE"),actualDataMAp.get("title"));
        assertEquals(expectedDAtaMap.get("CompleteT"),actualDataMAp.get("completed"));


    }


}
