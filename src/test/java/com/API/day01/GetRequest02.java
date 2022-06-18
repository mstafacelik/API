package com.API.day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {


    @Test
    public void test01() {

    /*
    https://restful-booker.herokuapp.com/booking url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    content type'inin "application/json" oldugunu
    Ve Status Line’in HTTP/1.1 200 OK test edin
     */


        // 1- api testi yaparken ilk olarak url(endpoint) belirlenmeli
        String uri = " https://restful-booker.herokuapp.com/booking";

        //2- beklenen sonuç(expected result) oluşturulur.
        // bu case de benden body doğrulaması istenmediği için şimdilik beklenen sonuç oluşturmuyoruz

        //3-  request gönder

        Response response = given().
                accept(ContentType.JSON).
                when().get(uri);

        response.prettyPrint();


        System.out.println("response.contentType() = " + response.getContentType());
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getStatusLine() = " + response.getStatusLine());


        //4-actual result oluştur
        //response body ile ilgili işlem yapmayacağımız için şimdi oluşturmayacağız

        //5-doğrulama yap(assertion)

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }

    @Test
    public void test02() {

          /*
           https://restful-booker.herokuapp.com/booking/1001 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 404 oldugunu
    ve Response body'sinin "Not Found" icerdigini
    ve Response body'sinin "API" icermedigini test edin

         */


        String uri="https://restful-booker.herokuapp.com/booking/100111";

        Response response=given().
                accept(ContentType.JSON).
                get(uri);

        response.prettyPrint();


        response.then().assertThat().statusCode(404);
        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("API"));

        // asString methodu ile json formatinda gelen response u Stringe cevirdik









    }
}