package com.API.day03;

import com.API.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest08 extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim
 */

    @Test
    public void test01() {

        // tek parametre kullanacagamiz icin pathParams degil pathParam kullaniyoruz
        spec03.pathParam("parametre1", "employees");


        Response response = given().
                accept("application/JSON").
                spec(spec03).
                when().
                get("/{parametre1}");


        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();


        System.out.println(jsonPath.getList("data.employee_name"));
        // Alternatif yazim-->>  System.out.println(jsonPath.getString("data.employee_name"));

        System.out.println(jsonPath.getString("data[2].employee_name"));
        // Alternatif yazim-->> System.out.println(jsonPath.getString("data.employee_name[2]"));

        System.out.println(jsonPath.getList("data.employee_name[0,1,2,3,4]"));
        System.out.println(jsonPath.getString("data.employee_name[-1]"));

        assertEquals(200, response.getStatusCode());
        // StatusCode'u jsonPath in icin atamadiginiz icin bunu kullandik

        assertEquals("Ashton Cox", jsonPath.getString("data[2].employee_name"));
        assertEquals("Doris Wilder", jsonPath.getString("data.employee_name[-1]"));



    }

}
