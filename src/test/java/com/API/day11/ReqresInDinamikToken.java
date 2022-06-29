package com.API.day11;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class ReqresInDinamikToken {


    public String dinamikTokenAl() {

        String url = "https://reqres.in/api/login";

        HashMap<String, Object> requestBody = new HashMap<>();

        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");

        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(requestBody).
                post(url);
        

        JsonPath jsonPath = response.jsonPath();
        String dinamikToken = jsonPath.getString("token");

        return dinamikToken;


    }


}
