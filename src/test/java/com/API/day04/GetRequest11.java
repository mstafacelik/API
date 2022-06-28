package com.API.day04;

import com.API.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest11 extends JsonPlaceHolderTestBase {

       /*
https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
 "completed": değerinin false
"title”: değerinin “quis ut nam facilis et officia qui”
"userId" sinin 1 ve
header değerlerinden
 "Via" değerinin “1.1 vegur” ve
 "Server" değerinin “cloudflare” olduğunu test edin…
 */

    /*
   1-) url oluştur
   2-) --expected data
   3-) request gönder
   4-) --actual data
   5-) assertion
     */

    @Test
    public void test01() {

        spec01.pathParams("parametre1", "todos",
                "parametre2", "2");

        HashMap<String, Object> expectedData = new HashMap<>();

        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userID", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

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

        // NOT : Header daki actual data isimlerinin yazimi case sensitiv degil. Ama response body deki datalarinki case sensitiv !
        // headers ve response body icinde ilk olarak hep actual data,
        // ikinci olarak ise bize data olarak verilen expected data yazilir



        // 2.Yöntem ========== Json Path ile Dogrulama =============

        JsonPath jsonPath = response.jsonPath();

        // StatusCode, ContentType ve Header'a ait bilgiler response body'de yer almadigi icin
        // asagidaki dogrulamayi response ile yapiyoruz.
        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("viA"));
        assertEquals(expectedData.get("Server"), response.getHeader("SeRVeR"));

        // Asagida response body'ye ait bilgiler yer aldigi icin dogrulamayi jsonPath ile yapiyoruz
        assertEquals(expectedData.get("userID"), jsonPath.getInt("userId"));
        assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));



        // 3. Yöntem =========== DE-Serialization ==================

        // --> Object Mapper
        // --> Pojo Class ile Map


        // NOT : jsonPath ve De-Serialization ile assert yaparken once expected data sonra actual data yazilir
        //       Matchers ile assert yaparken tam tersi, yani once actual data sonra expected data yazilir







    }


}
