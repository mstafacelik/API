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

        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();

        HashMap<String,Object> expectedData= (HashMap<String, Object>) jsonPlaceHolderTestData.setUpTestData();

        System.out.println(expectedData);

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
                statusCode((int) (expectedData.get("StatusCode"))).
                headers("viA", equalTo(expectedData.get("Via")), // dikkat-->header degil headerS !
                        "sErVeR", equalTo(expectedData.get("Server"))).
                body("userId", equalTo(expectedData.get("userID")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));

        // NOT : Header daki actual data isimlerinin yazimi case sensitiv degil. Ama response body deki datalarinki case sensitiv
        // headers ve response body icinde ilk olarak hep actual data,
        // ikinci olarak ise bize data olarak verilen expected data yazilir



        // 2.Yöntem ========== Json Path ile Dogrulama =============

        JsonPath jsonPath = response.jsonPath();

        // StatusCode, ContentType ve Header'a ait bilgiler response body'de yer almadigi icin
        // asagidaki dogrulamayi response ile yapiyoruz.
        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("viA"));
        assertEquals(expectedData.get("Server"), response.getHeader("SERVER"));

        // Asagida response body'ye ait bilgiler yer aldigi icin dogrulamayi jsonPath ile yapiyoruz
        assertEquals(expectedData.get("userID"), jsonPath.getInt("userId"));
        assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));



        // 3. Yöntem =========== DE-Serialization ==================

        // --> Object Mapper
        // --> Pojo Class ile Map





    }
}
