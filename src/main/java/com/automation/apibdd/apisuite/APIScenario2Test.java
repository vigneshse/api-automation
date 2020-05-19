package com.automation.apibdd.apisuite;

import com.automation.apibdd.common.ApiProcessor;
import com.automation.apibdd.common.JsonProcessor;
import com.automation.apibdd.model.ApiResponse;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.hamcrest.Matchers.equalTo;

public class APIScenario2Test {

    private JsonProcessor jsonProcessor;
    private ApiProcessor apiProcessor;
    private String methodName;
    private JsonObject prevResponse;
    private Response response;

    @BeforeClass
    public void setUp(){
        jsonProcessor = new JsonProcessor();
        apiProcessor = new ApiProcessor("http://www.mocky.io/v2/5ec32f01300000800039bf09");
        prevResponse = jsonProcessor.readJsonFile("shouldReturnDataForGet");
    }

    @BeforeMethod
    public void nameBefore(Method method)    {
        methodName = method.getName();
    }

    @Test
    public void shouldReturnDataForPost(){
        response = apiProcessor.getResponse();
        jsonProcessor.writeJsonFile(response.getBody(), methodName);
        response.then().assertThat().body("data", equalTo("8096"));
    }

    @Test(dependsOnMethods = {"shouldReturnDataForPost"})
    public void assertResponse(){
        Assert.assertNotEquals(response.getBody().jsonPath().get("data"), prevResponse.get("data"));
    }
}
