package com.API.day05;

import com.API.testBase.DummyTestBase;
import com.API.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest13_DE_Serialization extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
      Sondan 2. çalışanın maaşının 106450 olduğunu
      40,21 ve 19 yaslarında çalışanlar olup olmadığını
      11. Çalışan bilgilerinin
    {
            “id”:”11”
            "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
}
     gibi olduğunu test edin.

*/
    @Test
    public void test() {

        // 1. uri olustur
        spec03.pathParam("parametre1", "employees");

        // 2. expected data olustur
        DummyTestData dummyTestData = new DummyTestData();
        HashMap<String, Object> expectedDataMap = (HashMap<String, Object>) dummyTestData.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // 3. Request gönder
        Response response = given().
                accept(ContentType.JSON).
                when().
                spec(spec03).
                get("/{parametre1}");


        // 4. Actual data olustur (DE-Serialization veya JsonPath ile)


        HashMap<String, Object> actualDataMap = response.as(HashMap.class); // DE-Serialization
        System.out.println("actualDataMap = " + actualDataMap);

        JsonPath jsonPath = response.jsonPath(); // JsonPath


        // 5. Dogrulama yap

        // status kodun 200 olduğu
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        // besinci calışan isminin "Airi Satou" olduğu
        assertEquals(expectedDataMap.get("besinciCalisanIsmi"),
                ((Map) ((List) actualDataMap.get("data")).get(4)).get("employee_name"));

        //  çalısan sayısının 24 oldugu
        assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List) actualDataMap.get("data")).size());

        // Sondan 2. çalışanın maaşının 106450 olduğu
        int dataSize = ((List) actualDataMap.get("data")).size(); // actual data dan dönen size i bulduk
        assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"),
                ((Map) ((List) actualDataMap.get("data")).get(dataSize - 2)).get("employee_salary"));


        // 40,21 ve 19 yaslarında çalışanlar olup olmadığı

        List<Integer> actualYasList = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {

            actualYasList.add((Integer)((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));

        }

        System.out.println("actualYasList = " + actualYasList);


        assertTrue(actualYasList.containsAll( (List)expectedDataMap.get("arananYaslar")));


        // onbirinci calisan bilgileri

        assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_name"),
                ((Map)((List)actualDataMap.get("data")).get(10)).get("employee_name"));

        assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("id"),
                ((Map)((List)actualDataMap.get("data")).get(10)).get("id"));

        assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_salary"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

        assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));


















    }

}
