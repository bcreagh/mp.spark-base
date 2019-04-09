package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.mp.domain.Action;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.ConfigService;
import com.google.gson.Gson;

import java.util.ArrayList;

import static spark.Spark.*;

public class ListActions {

    private static final String SERVICE_NAME = ConfigService.getServiceName();

    public static void init() {
        listActions();
    }

    public static void listActions() {
        Gson gson = new Gson();
        get(String.format("/%s/actions", SERVICE_NAME), (request, response) -> {
            response.type("application/json");
            ArrayList<Action> actions = ActionService.getAllActions();
            String actionsAsJson = gson.toJson(actions);
            return actionsAsJson;
        });
    }
}
