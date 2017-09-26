package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.request.Request;
import ca.sukhsingh.actions.on.google.response.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Action executor is a helper class which elemenate the use of actions to check which method of call.
 * With help of action executor, user will be able to call a method name similar to the action name.
 * Only thing to worry about is, make sure you method name and action name is exactly same and also case senstive
 */
public class ActionExecutor {

    /**
     * a repository which will hold all the methods from user side
     */
    Object actionRepository;

    /**
     * A constructor dependency injection is being used here.
     * wherever user initiate ActionExecutor object the repository is required
     * @param actionRepository {@link Object} where all the methods are defined
     */
    public ActionExecutor(Object actionRepository) {
        this.actionRepository = actionRepository;
    }

    /**
     * This method will bring the response from the service class
     * for e.g. Response rs = executor.getResponse(request);
     * @param request {@link Request} a request object
     * @return {@link Response} a user constructed response object
     */
    public Response getResponse(Request request) {
        Method m = null;
        try {
            m = actionRepository.getClass().getMethod(request.getAction(),Request.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            return (Response)m.invoke(actionRepository, request);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
