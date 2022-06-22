package com.API.testData;

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


}
