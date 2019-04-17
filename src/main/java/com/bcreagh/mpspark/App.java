package com.bcreagh.mpspark;

import com.bcreagh.mpspark.exceptions.InitializationException;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.ListActions;
import com.bcreagh.mpspark.routes.TopicReadme;
import com.bcreagh.mpspark.routes.helloworld.HelloWorld;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.ConfigService;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static spark.Spark.*;


public class App 
{
    public static void main( String[] args ) throws IOException {
        initializeServices();
        port(ConfigService.getPort());
        initializeRouteClasses();
    }

    private static void initializeServices() throws IOException {
        ActionService.init();
        ConfigService.init();
    }

    private static void initializeRouteClasses() throws IOException {
        Reflections reflections = new Reflections("com.bcreagh.mpspark.routes");
        Set<Class<? extends BaseRoute>> routeClasses = reflections.getSubTypesOf(BaseRoute.class);
        for (Class<? extends BaseRoute> route : routeClasses) {
            initializeRoutes(route);
        }
    }

    private static void initializeRoutes(Class<? extends BaseRoute> klass) {
        try {
            List<Method> methods = getMethodsAnnotatedWithRoute(klass);
            for (Method method : methods) {
                Logger.log("Initializing route: " + method.getName());
                method.invoke(klass);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new InitializationException("There was a problem initializing the routes", ex);
        }

    }

    public static List<Method> getMethodsAnnotatedWithRoute(final Class<?> type) {
        final List<Method> methods = new ArrayList<>();
        Class<?> klass = type;
        while (klass != Object.class) {
            final List<Method> allMethods = new ArrayList<>(Arrays.asList(klass.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(MpRoute.class)) {
                    methods.add(method);
                }
            }
            klass = klass.getSuperclass();
        }
        return methods;
    }
}
