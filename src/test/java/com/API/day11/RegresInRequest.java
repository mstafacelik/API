package com.API.day11;

import org.junit.Test;

public class RegresInRequest {

    @Test
    public void test() {

        ReqresInDinamikToken reqresInDinamikToken=new ReqresInDinamikToken();
        System.out.println("dinamikToken= " + reqresInDinamikToken.dinamikTokenAl());

        // API Key iki sekilde yazilabilir:
        // 1- request->header(s) icinde
        // 2- uri olustururken queryParam ile


    }


}
