package com.bcreagh.mpspark;

import com.bcreagh.mpspark.routes.ListActions;
import com.bcreagh.mpspark.services.ActionService;

import java.io.IOException;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        ActionService.init();
        ListActions.init();
        get("/hello", (req, res) -> "Hello World");
    }
}
