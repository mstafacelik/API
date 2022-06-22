package com.API.day04;

import com.API.testBase.DummyTestBase;
import com.API.testBase.JsonPlaceHolderTestBase;
import com.API.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest11_TestData extends JsonPlaceHolderTestBase {

    @Test
    public void test01() {

        spec01.pathParams("parametre1", "todos",
                "parametre2", "2");

        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();

        HashMap<String, Object> expectedDataMap = (HashMap<String, Object>) jsonPlaceHolderTestData.setUpTestData();

        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given().
                accept("application/JSON").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();


        // 1.Yöntem ========== Matchers Class ile Dogrulama =============

        response.
                then().
                assertThat().
                statusCode((int) (expectedDataMap.get("StatusCode"))).
                headers("viA", equalTo(expectedDataMap.get("Via")), // dikkat-->header degil headerS !
                        "sErVeR", equalTo(expectedDataMap.get("Server"))).
                body("userId", equalTo(expectedDataMap.get("userID")),
                        "title", equalTo(expectedDataMap.get("title")),
                        "completed", equalTo(expectedDataMap.get("completed")));

        // NOT : Header daki actual data isimlerinin yazimi case sensitiv degil. Ama response body deki datalarinki case sensitiv
        // headers ve response body icinde ilk olarak hep actual data,
        // ikinci olarak ise bize data olarak verilen expected data yazilir


        // 2.Yöntem ========== Json Path ile Dogrulama =============

        JsonPath jsonPath = response.jsonPath();

        // StatusCode, ContentType ve Header'a ait bilgiler response body'de yer almadigi icin
        // asagidaki dogrulamayi response ile yapiyoruz.
        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"), response.getHeader("viA"));
        assertEquals(expectedDataMap.get("Server"), response.getHeader("sErVeR"));

        // Asagida response body'ye ait bilgiler yer aldigi icin dogrulamayi jsonPath ile yapiyoruz
        assertEquals(expectedDataMap.get("userID"), jsonPath.getInt("userId"));
        assertEquals(expectedDataMap.get("title"), jsonPath.getString("title"));
        assertEquals(expectedDataMap.get("completed"), jsonPath.getBoolean("completed"));


        // 3. Yöntem =========== DE-Serialization ================== ( Object Mapper,  Pojo Class ile Map )


        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
        // response dan gelen datayi map gibi(as) alip, actual dataya atadik


        // StatusCode, ContentType ve Header'a ait bilgiler response body'de yer almadigi icin
        // HashMap icinde bu bilgiler yer almaz. Bunlarin dogrulamasi da response ile yapiyoruz tipki yukardaki gibi

        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"), response.getHeader("viA"));
        assertEquals(expectedDataMap.get("Server"), response.getHeader("sErVeR"));


        // DE-Serialization'da da HashMap in icine gelen kisim sadece body kismi json path deki gibi.

        assertEquals(expectedDataMap.get("userID"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));


    }
}
