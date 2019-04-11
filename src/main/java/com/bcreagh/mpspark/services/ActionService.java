package com.bcreagh.mpspark.services;

import com.bcreagh.mpspark.mp.domain.Action;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ActionService {

    private static ArrayList<Action> actions = new ArrayList<>();

    public static void init() throws IOException {
        try {
            FileReader fileReader = new FileReader("actions.json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Gson gson = new Gson();
            actions = gson.fromJson(bufferedReader, new TypeToken<ArrayList<Action>>(){ }.getType());
        } catch (JsonParseException ex) {
            System.out.println("There was a problem parsing the actions.json file");
            throw ex;
        }
    }

    public static ArrayList<Action> getAllActions() {
        return actions;
    }

    public static Action getAction(String route) {
        Action action = null;
        for(Action actionFromList : actions) {
            if (actionFromList.getRoute().equals(route)) {
                action = actionFromList;
                break;
            }
        }
        if (action == null) {
            throw new IllegalArgumentException(String.format("An action could not be found for the route %s", route));
        }
        return new Action(action);
    }
}
