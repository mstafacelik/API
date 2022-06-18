package com.API.day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest01 {

   /*
    https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
    donecek cevap(response) icin
HTTP status kodunun 200
Content Type’in Json
Ve Status Line’in HTTP/1.1 200 OK
Oldugunu test edin
   */

    @Test
    public void test01() {



        // 1- api testi yaparken ilk olarak url(endpoint) belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";

        //2- beklenen sonuç(expected result) oluşturulur.
        // bu case de benden body doğrulaması istenmediği için şimdilik beklenen sonuç oluşturmuyoruz

        //3-  request gönder
        Response response = given().
                accept("application/json").  // accept("application/json") kullanılabilir.
                        when().
                get(url);

         response.prettyPrint();

        //4-actual result oluştur
        //response body ile ilgili işlem yapmayacağımız için şimdi oluşturmayacağız

        //5-doğrulama yap(assertion)

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.getStatusLine() = " + response.getStatusLine());
        System.out.println("response.getHeaders() = " + response.getHeaders());


        // 1. yontem

        /*
        Assert.assertEquals(200,response.getStatusCode());
        // expected kismi bize task olarak verilen degerdir
        // status code int deger döndürür

        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine());

        */

        // 2. yontem

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");


    }
}
