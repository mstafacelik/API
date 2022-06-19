package com.API.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;

public class HerOkuAppTestBase {


    protected RequestSpecification spec02;
    // spec01 desek de olurdu. Zaten bu safyaya extends deildiginde, cild class lar bu sayfadaki spec e ulasir.

    @Before
    public void setUp() {


        // RequestSpecification requestSpecification=new RequestSpecification --->> bu sekilde yapamayiz
        // cunku RequestSpecification bir interface. Interface lerden nesne üretilemez, sadece data type olarak kullnabiliriz
        // burada polymorphism var.

        spec02 = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();


    }


}
