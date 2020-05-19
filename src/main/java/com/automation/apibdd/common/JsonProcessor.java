package com.automation.apibdd.common;

import com.automation.apibdd.model.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.ResponseBody;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonProcessor {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String jsonExt = ".json";

    public void writeJsonFile(ResponseBody responseBody, String filePath) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(filePath + jsonExt));
            JsonObject jsonObject = gson.fromJson(responseBody.prettyPrint(), JsonObject.class);
            gson.toJson(jsonObject, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonObject readJsonFile(String filePath) {
        JsonObject jsonObject = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath + jsonExt));
            jsonObject = gson.fromJson(reader, JsonObject.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
