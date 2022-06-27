package com.API.testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {


    public Map<String, Object> setUpTestData() {

        HashMap<String, Object> expectedData = new HashMap<>();

        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userID", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);


        return expectedData;
    }

    public JSONObject setUpRequestBodyAndExpectedData() {

        JSONObject expectedRequest = new JSONObject();

        expectedRequest.put("UserId", 55);
        expectedRequest.put("title", "tidy your room");
        expectedRequest.put("completed", false);
        expectedRequest.put("statusCode",201);


        return expectedRequest;


    }

   /* {
            "userId": 21,
            "title": "Wash the dishes",
            "completed": false
    }

    */

    public JSONObject setUpPutData(){

        JSONObject expectedRequest=new JSONObject();

        expectedRequest.put("userId",21);
        expectedRequest.put("title","Wash the dishes");
        expectedRequest.put("completed",false);
        expectedRequest.put("statusCode",200);

        return expectedRequest;



    }




}
