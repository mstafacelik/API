package com.API.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;

public class DummyTestBase {


    protected RequestSpecification spec03;

    @Before

    public void setUp(){

        spec03=new RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1").
                build();



    }

}
