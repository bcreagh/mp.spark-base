package com.bcreagh.mpspark;

import com.bcreagh.mpspark.routes.ListActions;
import com.bcreagh.mpspark.routes.TopicReadme;
import com.bcreagh.mpspark.routes.helloworld.HelloWorld;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.ConfigService;

import java.io.IOException;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        initializeServices();
        port(ConfigService.getPort());
        initializeRoutes();
        get("/hello", (req, res) -> "Hello World");
    }

    private static void initializeServices() throws IOException {
        ActionService.init();
        ConfigService.init();
    }

    private static void initializeRoutes() throws IOException {
        ListActions.init();
        TopicReadme.init();
        HelloWorld.init();
    }
}
