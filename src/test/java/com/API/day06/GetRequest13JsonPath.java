package com.API.day06;

import com.API.testBase.DummyTestBase;
import com.API.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest13JsonPath extends DummyTestBase {


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

        // 1. Uri olustur
        spec03.pathParam("parametre1", "employees");

        // 2. Expected data olustur
        DummyTestData dummyTestData = new DummyTestData();
        HashMap<String, Object> expectedDataMap = (HashMap<String, Object>) dummyTestData.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // 3. Request gönder
        Response response = given().
                accept(ContentType.JSON).
                when().
                spec(spec03).
                get("/{parametre1}");

        response.prettyPrint();

        //4. JsonPath ile Dogrulama

        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("besinciCalisanIsmi"), jsonPath.getString("data[4].employee_name"));
        assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"), jsonPath.getInt("data[-2].employee_salary"));
        assertEquals(expectedDataMap.get("calisanSayisi"), jsonPath.getList("data.id").size());
        assertTrue(jsonPath.getList("data.employee_age").containsAll((List) expectedDataMap.get("arananYaslar")));

        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_name"), jsonPath.getString("data[10].employee_name"));
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_salary"), jsonPath.getInt("data[10].employee_salary"));
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("id"), jsonPath.getInt("data[10].id"));
        assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("profile_image"), jsonPath.getString("data[10].profile_image"));




    }
}