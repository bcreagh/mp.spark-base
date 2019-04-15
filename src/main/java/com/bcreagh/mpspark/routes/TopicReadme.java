package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.mp.domain.Readme;
import com.bcreagh.mpspark.services.FileService;
import java.io.IOException;

import static spark.Spark.*;

public class TopicReadme extends BaseRoute {

    private static final Readme readme = new Readme();

    public static void init() throws IOException {
        loadReadme();
        readme();
    }

    public static void readme() {
        get(String.format("/%s/readme", SERVICE_NAME), (request, response) -> {
            return jsonResponse(readme, response);
        });
    }

    private static void loadReadme() throws IOException {
        String readmeAsString = FileService.readFileFromResources("README.md", "UTF-8");
        readme.setData(readmeAsString);
    }
}
