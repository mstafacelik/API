package com.API.day04;

import com.API.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest10 extends DummyTestBase {


     /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde
Dönen response un
 Status kodunun 200,
 1)10’dan büyük tüm id’leri ekrana yazdırın ve
10’dan büyük 14 id olduğunu,
 2)30’dan küçük tüm yaşları ekrana yazdırın ve
  bu yaşların içerisinde en büyük yaşın 23 olduğunu
 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
  bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/JSON")
                .spec(spec03).
                when().
                get("/{parametre1}");

        JsonPath jsonPath = response.jsonPath();

        //Groovy dili javanin alt dilidir. Bu dil yardimi ile loop kullanmadan
        // gelen responsedaki değerler bir şarta bağli olarak listeye yazdirilabilir


        List<Integer> idList = jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println("idList = " + idList);

        assertEquals(14, idList.size());


        List<Integer> yasList = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");

        System.out.println("yasList = " + yasList);


       Collections .sort(yasList);
        //---> expected data primitiv, actual data Wrapper oldugu icin actual i da primitiv yapiyoruz
        assertEquals(23, (int) yasList.get(yasList.size() - 1));

        //---> Alternatif yazim
        // expected data primitiv, actual data Wrapper oldugu icin expected i da Wrapper yapiyoruz
        // assertEquals( (Integer)23, yasList.get(yasList.size() - 1));

        List<String> EmployeeNameList = jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("EmployeeNameList = " + EmployeeNameList);

        assertTrue(EmployeeNameList.contains("Charde Marshall"));


    }


}
