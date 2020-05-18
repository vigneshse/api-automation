package com.automation.apibdd.common;

import com.google.gson.Gson;
import io.restassured.response.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonProcessor {

    private Gson gson;
    private final String jsonExt = ".json";

    public JsonProcessor(Gson gson){
        this.gson = gson;
    }

    public void writeJsonFile(ResponseBody responseBody, String filePath) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(filePath + jsonExt));
            gson.toJson(responseBody.prettyPrint(), writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ResponseBody readJsonFile(String filePath) {
        ResponseBody responseBody = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath + jsonExt));
            responseBody = gson.fromJson(reader,ResponseBody.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}
