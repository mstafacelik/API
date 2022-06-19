package com.API.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderTestBase {

    // RequestSpecification bir Interface dir.
    // birden fazla API endpoint te calisacagimiz icin spec01 dedik
    // calisilan sirkette endpoit degismeyecegi icin spec demek yeterli

    protected RequestSpecification spec01;


    @Before
    public void setUp() {

        spec01 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();

    }


}
