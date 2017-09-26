package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.request.Request;
import ca.sukhsingh.actions.on.google.response.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ActionExecutor {

    Object methodRepository;

    public ActionExecutor(Object methodRepository) {
        this.methodRepository = methodRepository;
    }

    public Response getResponse(Request request) {
        Method m = null;
        try {
            m = methodRepository.getClass().getMethod(request.getAction(),Request.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            return (Response)m.invoke(methodRepository, request);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
