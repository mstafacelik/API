package com.API.testData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class DummyTestData {
    /*
   http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
   Status kodun 200 olduğunu,
     5. Çalışan isminin "Airi Satou" olduğunu,
     çalışan sayısının 24 olduğunu,
   Sondan 2. çalışanın maaşının 106450 olduğunu,
40,21 ve 19 yaslarında çalışanlar olup olmadığını,
11. Çalışan bilgilerinin
   {
           “id”:”11”
           "employee_name": "Jena Gaines",
           "employee_salary": "90560",
           "employee_age": "30",
           "profile_image": "" }
} gibi olduğunu test edin.

*/
    public Map<String, Object> setUpTestData() {


        List<Integer> arananYasList = new ArrayList<>();
        // List<Integer> calisanlarYasList=asList(19,21,40); --> Alternatif yazim

        arananYasList.add(19);
        arananYasList.add(21);
        arananYasList.add(40);

        HashMap<String, Object> id11BilgilerMap = new HashMap<>();
        id11BilgilerMap.put("id", 11);
        id11BilgilerMap.put("employee_name", "Jena Gaines");
        id11BilgilerMap.put("employee_salary", 90560);
        id11BilgilerMap.put("employee_age", 30);
        id11BilgilerMap.put("profile_image", "");

        HashMap<String, Object> expectedData = new HashMap<>();

        expectedData.put("statusCode", 200);
        expectedData.put("besinciCalisanIsmi", "Airi Satou");
        expectedData.put("calisanSayisi", 24);
        expectedData.put("sondanIkinciCalisanMaasi", 106450);
        expectedData.put("arananYaslar", arananYasList);
        expectedData.put("onbirinciCalisan", id11BilgilerMap);


        return expectedData;
    }



    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */


    public HashMap<String, Object> setUpTestData02() {

        HashMap<String, Object> expectedData = new HashMap<>();

        expectedData.put("statusCode", 200);
        expectedData.put("enYuksekMaas", 725000);
        expectedData.put("ikinciEnYuksekMaas", 675000);
        expectedData.put("enKucukYas", 19);

        return expectedData;

    }

    public HashMap<String, String> setUpRequestBody() {

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "mustingo");
        requestBody.put("salary", "5000");
        requestBody.put("age", "99");

        return requestBody;


    }

    public HashMap<String, Object> setUpExpectedTestData() {

        //  Map<String, Object> dataMapInner = new HashMap<>();

        //  dataMapInner.put("name", "mustingo");
        //  dataMapInner.put("salary", "5000");
        //  dataMapInner.put("age", "99");

        HashMap<String, Object> expectedData = new HashMap<>();

        expectedData.put("StatusCode", 200);
        expectedData.put("StatuS", "success");
        // expectedData.put("data", dataMapInner);

        expectedData.put("MessagE", "Successfully! Record has been added.");

        return expectedData;


    }


}
