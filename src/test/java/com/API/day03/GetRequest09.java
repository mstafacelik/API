package com.API.day03;

import com.API.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest09 extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde,
status kodun 200,
gelen body de,
5. çalışanın isminin "Airi Satou" olduğunu ,
6. çalışanın maaşının "372000" olduğunu ,
Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
     */


    @Test
    public void test01() {

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/JSON").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.getStatusCode());
        assertEquals("Airi Satou", jsonPath.getString("data[4].employee_name"));

// =======================Matcher(s) Class ile Dogrulama ======================
        /* response.
                 then().
                 assertThat().
                 statusCode(200).
                 contentType(ContentType.JSON).
                 body("data.id",hasSize(24),
                         "data[4].employee_name","Airi Satou",
                 "data.employee_name", hasItem("Rhona Davidson"),
                 "data.employee_age", hasItems(21,23,62));

         */


        // ================ JsonPath ile Dogrulama ==========================

        // StatusCode ile contentType response body de yer almaz,
        // Matcher(s) Class ile ya da Junit Assert ile  dogrulama yapmak zorundayiz.
        assertEquals(200, response.getStatusCode()); // Alternatif yazim-->> assertTrue(response.getStatusCode()==200);
        response.then().assertThat().contentType("application/JSON").statusCode(200);


        int actualSize = jsonPath.getList("data.id").size();
        assertEquals(24, actualSize);
        assertEquals("Airi Satou", jsonPath.getString("data[4].employee_name"));
        assertEquals(372000, jsonPath.getInt("data[5].employee_salary")); // int oldugu icin getInt

        assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));


        List<Integer> arananyaslar = new ArrayList<>();
        arananyaslar.add(21);
        arananyaslar.add(23);
        arananyaslar.add(61);

        //Alternatif yazim ArrayList icin--->> List<Integer> arananyaslar= Arrays.asList(21,23,61);

        assertTrue((jsonPath.getList("data.employee_age")).containsAll(arananyaslar));

    }


}
