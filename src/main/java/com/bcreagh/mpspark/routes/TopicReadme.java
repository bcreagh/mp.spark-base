package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.mp.domain.Readme;
import com.bcreagh.mpspark.services.ConfigService;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static spark.Spark.*;

public class TopicReadme {

    private static final String SERVICE_NAME = ConfigService.getServiceName();
    private static final Readme readme = new Readme();

    public static void init() throws IOException {
        loadReadme();
        readme();
    }

    public static void readme() {
        Gson gson = new Gson();
        get(String.format("/%s/readme", SERVICE_NAME), (request, response) -> {
            response.type("application/json");
            String readmeAsJson = gson.toJson(readme);
            return readmeAsJson;
        });
    }

    private static void loadReadme() throws IOException {
        byte[] readmeData = Files.readAllBytes(Paths.get("README.md"));
        String readmeAsString = new String(readmeData, "UTF-8");
        readme.setData(readmeAsString);
    }
}
