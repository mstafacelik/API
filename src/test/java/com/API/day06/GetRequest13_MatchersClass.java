package com.API.day06;

import com.API.testBase.DummyTestBase;
import com.API.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13_MatchersClass extends DummyTestBase {


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

        // 4. Matchers ile Dogrulama

        // NOT : jsonPath ve De-Serialization ile assert yaparken once expected data sonra actual data yazilir
        //       Matchers ile assert yaparken tam tersi, yani once actual data sonra expected data yazilir


        response.
                then().
                assertThat().
                statusCode((Integer) expectedDataMap.get("statusCode")).
                body("data[4].employee_name", equalTo(expectedDataMap.get("besinciCalisanIsmi")),
                        "data.id", hasSize((Integer) expectedDataMap.get("calisanSayisi")),
                        "data[-2].employee_salary", equalTo(expectedDataMap.get("sondanIkinciCalisanMaasi")),
                        "data.employee_age", hasItems(((List) expectedDataMap.get("arananYaslar")).get(0),
                                ((List<?>) expectedDataMap.get("arananYaslar")).get(1),
                                ((List<?>) expectedDataMap.get("arananYaslar")).get(2)),
                        "data[10].employee_name", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_name")),
                        "data[10].employee_salary", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_salary")),
                        "data[10].employee_age", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_age")),
                        "data[10].profile_image", equalTo(((Map) expectedDataMap.get("onbirinciCalisan")).get("profile_image")));

    }
}

