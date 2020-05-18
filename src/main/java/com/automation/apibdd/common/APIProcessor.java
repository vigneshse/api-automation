package com.automation.apibdd.common;

import io.restassured.response.Response;
import static io.restassured.RestAssured.get;

public class APIProcessor {

    private String url;

    public APIProcessor(String url){
        this.url = url;
    }
    public Response getResponse(){
        return get(this.url);
    }
}
