package com.bcreagh.mpspark.routes.helloworld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import com.bcreagh.mpspark.mp.domain.ActionResult;
import com.bcreagh.mpspark.mp.utilities.Stopwatch;
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
        hello_world_post();
        hello_world_with_route();
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

    public static void hello_world_post() {
        post(String.format("/%s/hello-world", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            Stopwatch stopwatch = new Stopwatch();
            HelloWorldInput input = gson.fromJson(request.body(), HelloWorldInput.class);

            Logger.log("Handling the POST request", result);
            stopwatch.start();
            result.setInput(input.getInput());
            result.setOutput("Hello " + input.getInput());
            long performance = stopwatch.stop();
            result.setPerformance(performance);

            response.type("application/json");
            return gson.toJson(result);
        });
    }

    public static void hello_world_with_route() {
        post(String.format("/%s/hello-world/with-route", SERVICE_NAME), (request, response) -> {
            ActionResult result = new ActionResult();
            Stopwatch stopwatch = new Stopwatch();
            String input = "- this response is from the 'with-route' route in spark base!";

            Logger.log("Handling the POST request", result);
            stopwatch.start();
            result.setInput(input);
            result.setOutput("Hello " + input);
            long performance = stopwatch.stop();
            result.setPerformance(performance);

            response.type("application/json");
            return gson.toJson(result);
        });
    }
}
