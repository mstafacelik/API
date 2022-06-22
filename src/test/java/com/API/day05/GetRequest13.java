package com.API.day05;

import com.API.testBase.DummyTestBase;
import com.API.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyTestBase {

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
} gibi olduğunu test edin.

*/
    @Test
    public void test(){

        // 1. uri olustur
        spec03.pathParam("parametre1","employees");

        // 2. expected data olustur
        DummyTestData dummyTestData=new DummyTestData();
        HashMap<String,Object> expectedDataMap= (HashMap<String, Object>) dummyTestData.setUpTestData();
        System.out.println("expectedDataMap = " + expectedDataMap);

        // 3. Request gönder
        Response response=given().
                accept(ContentType.JSON).
                when().
                spec(spec03).
                get("/{parametre1}");

        response.prettyPrint();

        // 4. Actual data olustur

        // 5. Dogrulama yap


















    }















}
