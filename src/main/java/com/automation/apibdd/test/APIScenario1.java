package com.automation.apibdd.test;

import static org.hamcrest.Matchers.equalTo;

import com.automation.apibdd.common.APIProcessor;
import com.automation.apibdd.common.JsonProcessor;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class APIScenario1 {

    private JsonProcessor jsonProcessor;
    private APIProcessor apiProcessor;
    private String methodName;

    @BeforeClass
    public void setUP(){
        jsonProcessor = new JsonProcessor(new Gson());
        apiProcessor = new APIProcessor("http://www.mocky.io/v2/5ec2d4e32f0000adbcc35665");
    }

    @BeforeMethod
    public void nameBefore(Method method)    {
        methodName = method.getName();
    }

    @Test
    public void shouldReturnSuccessForAPICall(){
        Response response = apiProcessor.getResponse();
        jsonProcessor.writeJsonFile(response.getBody(), methodName);
        response.then().assertThat().body("name", equalTo("John"));
    }
}
