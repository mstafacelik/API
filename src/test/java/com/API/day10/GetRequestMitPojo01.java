package com.API.day10;

import com.API.pojos.Data;
import com.API.pojos.DummyPojoOuter;
import com.API.testBase.DummyTestBase;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestMitPojo01 extends DummyTestBase {

     /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
     Status code is 200
          {
             "status": "success",
             "data": {
                     "id": 1,
                      "employee_name": "Tiger Nixon",
                      "employee_salary": 320800,
                       "employee_age": 61,
                       "profile_image": ""
                   },
              "message": "Successfully! Record has been fetched."
             }
 */


    @Test
    public void test(){

        //1- uri
        spec03.pathParams("parametre1","employee","parametre2",1);

        //2- expected data

        Data dataInner=new Data(1,"Tiger Nixon",320800,61,"");
        DummyPojoOuter expectedData=new DummyPojoOuter("success",dataInner,"Successfully! Record has been fetched.");
        System.out.println("expectedData = " + expectedData);

        //3- Request gonder

        Response response=given().
                contentType(ContentType.JSON).
                when().
                spec(spec03).
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // 4- actual data olustur ve dogrula

        DummyPojoOuter actualData=response.as(DummyPojoOuter.class);


        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        assertEquals(expectedData.getData().getemployee_name(),actualData.getData().getemployee_name());
        assertEquals(expectedData.getData().getemployee_salary(),actualData.getData().getemployee_salary());
        assertEquals(expectedData.getData().getemployee_age(),actualData.getData().getemployee_age());
        assertEquals(expectedData.getData().getprofile_image(),actualData.getData().getprofile_image());
        assertEquals(expectedData.getMessage(),actualData.getMessage());



        // ==============SERIALIZATION==================

        // Serialization java yapisinda olan datalari json formatina donusturme islemidir
        //1- Gson sinifindan obje uretilir

        Gson gson=new Gson();

        String jsonFromJava=gson.toJson(actualData);

        System.out.println("jsonFromJava = " + jsonFromJava);


    }



}
