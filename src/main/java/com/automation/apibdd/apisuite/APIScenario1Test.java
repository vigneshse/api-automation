package com.automation.apibdd.apisuite;

import static org.hamcrest.Matchers.equalTo;

import com.automation.apibdd.common.ApiProcessor;
import com.automation.apibdd.common.JsonProcessor;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class APIScenario1Test {

    private JsonProcessor jsonProcessor;
    private ApiProcessor apiProcessor;
    private String methodName;

    @BeforeClass
    public void setUp(){
        jsonProcessor = new JsonProcessor();
        apiProcessor = new ApiProcessor("http://www.mocky.io/v2/5ec32e25300000660039bf06");
    }

    @BeforeMethod
    public void nameBefore(Method method)    {
        methodName = method.getName();
    }

    @Test
    public void shouldReturnDataForGet(){
        Response response = apiProcessor.getResponse();
        jsonProcessor.writeJsonFile(response.getBody(), methodName);
        response.then().assertThat().body("data", equalTo("4096"));
    }
}
