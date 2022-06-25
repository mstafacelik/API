package com.API.day06;

import com.API.testBase.DummyTestBase;
import com.API.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest14 extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */


    @Test
    public void test01() {

        // 1. uri olustur
        spec03.pathParam("parametre1", "employees");

        // 2. expexted data olustur

        DummyTestData dummyTestData = new DummyTestData();

        HashMap<String, Object> expectedDataMap = dummyTestData.setUpTestData02();

        System.out.println("expectedDataMap = " + expectedDataMap);

        // 3. request gönder

        Response response = given().accept(ContentType.JSON).when().spec(spec03).get("/{parametre1}");
        response.prettyPrint();

        // 4. actual data olustur ve dogrula
        // -->Jsonpath ile

        JsonPath jsonPath = response.jsonPath();

        // Status kodun 200 olduğunu,
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        // En yüksek maaşın 725000 olduğunu,
        List<Integer> maasList = jsonPath.getList("data.employee_salary");
        Collections.sort(maasList);
        System.out.println("JsonPath maasListSirali = " + maasList);

        assertEquals(expectedDataMap.get("enYuksekMaas"), maasList.get(maasList.size() - 1));


        // En küçük yaşın 19 olduğunu,
        List<Integer> yaslarList = jsonPath.getList("data.employee_age");

        Collections.sort(yaslarList);
        System.out.println("yaslarListSirali = " + yaslarList);

        assertEquals(expectedDataMap.get("enKucukYas"), yaslarList.get(0));

        // İkinci en yüksek maaşın 675000
        assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasList.get(maasList.size() - 2));


        // -->DE-Serialization ile

        HashMap<String, Object> actualData = response.as(HashMap.class);

        // Status kodun 200 olduğunu,
        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        // En yüksek maaşın 725000 olduğunu
        List<Integer> maaslarList = new ArrayList<>();
        int maaslarListSize = ((List) actualData.get("data")).size();

        for (int i = 0; i < maaslarListSize; i++) {

            maaslarList.add(((Integer) ((Map) ((List) actualData.get("data")).get(i)).get("employee_salary")));
        }

        Collections.sort(maaslarList);

        assertEquals(expectedDataMap.get("enYuksekMaas"), maasList.get(maaslarListSize - 1));

        // İkinci en yüksek maaşın 675000
        assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasList.get(maaslarListSize - 2));


        // En küçük yaşın 19 olduğunu,

        List<Integer> yaslarListesi = new ArrayList<>();
        int yaslarListesiSize = ((List<?>) actualData.get("data")).size();

        for (int i = 0; i < yaslarListesiSize; i++) {

            yaslarListesi.add(
                    ((Map<String, Integer>) ((List<?>) actualData.get("data")).get(i)).get("employee_age")
            );
        }
        Collections.sort(yaslarListesi);
        System.out.println("DE-Serialization yaslarListesiSirali = " + yaslarListesi);

        assertEquals(expectedDataMap.get("enKucukYas"), yaslarList.get(0));

    }


}
