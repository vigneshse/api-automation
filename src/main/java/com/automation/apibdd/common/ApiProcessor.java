package com.automation.apibdd.common;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;

public class ApiProcessor {

    private String url;

    public ApiProcessor(String url){
        this.url = url;
    }
    public Response getResponse(){
        return get(this.url);
    }

    public Response postResponse(Map params){
        return post(this.url, params);
    }
}
