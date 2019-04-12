package com.bcreagh.mpspark.routes.helloworld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import com.google.gson.Gson;
import static spark.Spark.*;

import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.ConfigService;
import com.bcreagh.mpspark.services.FileService;

public class HelloWorld {

    private static final String SERVICE_NAME = ConfigService.getServiceName();
    private static final Gson gson = new Gson();

    public static void init() {
        hello_world_get();
    }

    public static void hello_world_get() {
        get(String.format("/%s/hello-world", SERVICE_NAME), (request, response) -> {
            response.type("application/json");
            Logger.log("Handling the GET request");
            Action action = ActionService.getAction("hello-world");
            String readmeAsString = FileService.readFileFromResources("routes/helloworld/helloWorld.md", "UTF-8");
            action.getReadme().setData(readmeAsString);
            return gson.toJson(action);
        });
    }
}
