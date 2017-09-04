package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.request.result.Context;
import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.systemIntent.SystemIntent;
import ca.sukhsingh.actions.on.google.response.data.google.systemIntent.SystemIntentData;
import ca.sukhsingh.actions.on.google.response.data.google.Data;
import ca.sukhsingh.actions.on.google.response.data.google.Google;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse.SimpleResponse;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhsingh on 2017-08-28.
 */
/**
 * This is the class that handles the communication with API.AI's fulfillment API.
 */
public class ApiAiApp extends AssistantApp{

    Logger logger = Logger.getLogger(ApiAiApp.class);

    public ApiAiApp() {
    }

    public boolean isRequestFromApiAi(String key, String value) {
        return false;
    }

    public String getIntent() {
        return null;
    }

    public Object getArgument(String agrName) {
        return null;
    }

    public Object getContextArgument(String contextName, String argName) {
        return null;
    }

    public RichResponse getIncomingRichResponse() {
        return null;
    }

    public Object getIncomingList() {
        return null;
    }

    public Object getIncomingCarousel() {
        return null;
    }

    public String getSelectedOption() {
        return null;
    }

    /**
     * Tells the Assistant to render the speech response and close the mic.
     *
     * @param textToSpeech
     * @return {@link Response}
     */
    public Response tell(String textToSpeech) {
        if (textToSpeech.isEmpty()) {
            return null;
        }

        return buildResponse(textToSpeech, false, null);
    }

    /**
     * Tells the Assistant to render the speech response and close the mic.
     *
     * @param textToSpeech
     * @param displayText
     * @return {@link Response}
     */
    public Response tell(String textToSpeech, String displayText) {
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText(displayText);
        if (Util.isSsml(textToSpeech)) {
            simpleResponse.setSsml(textToSpeech);
        } else {
            simpleResponse.setTextToSpeech(textToSpeech);
        }
        return buildResponse(simpleResponse, false, null);
    }

    /**
     * Tells the Assistant to render the speech response and close the mic.
     *
     * @param simpleResponse
     * @return {@link Response}
     */
    public Response tell(SimpleResponse simpleResponse) {
        return buildResponse(new RichResponse().addSimpleResponse(simpleResponse), false, null);
    }

    /**
     * Tells the Assistant to render the speech response and close the mic.
     *
     * @param richResponse
     * @return {@link Response}
     */
    public Response tell(RichResponse richResponse) {
        return buildResponse(richResponse, false, null);
    }

    /**
     * Asks to collect the user's input.
     *
     * @param textToSpeech
     * @param noInputPrompts
     * @return {@link Response}
     */
    public Response ask(String textToSpeech, String [] noInputPrompts) {
        if (textToSpeech.isEmpty()) {
            return null;
        }

        return buildResponse(textToSpeech, true, noInputPrompts);
    }

    /**
     * Asks to collect the user's input.
     *
     * @param {@link String} textToSpeech
     * @return {@link Response}
     */
    public Response ask(String textToSpeech) {
        if (textToSpeech.isEmpty()) {
            return null;
        }

        return buildResponse(textToSpeech, true, null);
    }

    /**
     * Asks to collect the user's input.
     *
     * @param {@link String} textToSpeech
     * @param {@link String} displayText
     * @return {@link Response}
     */
    public Response ask(String textToSpeech, String displayText) {
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText(displayText);
        if (Util.isSsml(textToSpeech)) {
            simpleResponse.setSsml(textToSpeech);
        } else {
            simpleResponse.setTextToSpeech(textToSpeech);
        }
        simpleResponse.setTextToSpeech(textToSpeech);
        return buildResponse(simpleResponse, true, null);
    }

    public Response ask(SimpleResponse simpleResponse) {
        return buildResponse(new RichResponse().addSimpleResponse(simpleResponse), true, null);
    }

    /**
     * Asks to collect the user's input.
     *
     * @param richResponse
     * @return {@link Response}
     */
    public Response ask(RichResponse richResponse) {
        return buildResponse(richResponse, true, null);
    }


    /**
     * Asks to collect the user's input with a list.
     *
     * @param {@link String}|{@link RichResponse}|{@link SimpleResponse} inputPrompt
     * @param {@link ca.sukhsingh.actions.on.google.response.data.google.systemIntent.List} list
     * @return {@link Response}
     */
    public Response askWithList(Object inputPrompt, ca.sukhsingh.actions.on.google.response.data.google.systemIntent.List list) {
        if (Util.isNull(inputPrompt)) {
            logger.error("Invalid inputpromt");
            return null;
        }

        if (Util.isNull(list)) {
            logger.error("Invalid list");
            return null;
        }

        if (list.getItems().size() < 2) {
            logger.error("List requires at least 2 items");
            return null;
        }

        Response response = buildResponse(inputPrompt, true, null);
        if (Util.isNull(response)) {
            logger.error("Error in building response");
            return null;
        }

        SystemIntent systemIntent = new SystemIntent();
        systemIntent.setIntent(StandardIntents.OPTION);

        //TODO if(this.isNotApiVersionOne_()) {
        SystemIntentData data = new SystemIntentData();
        data.setType(InputValueDataTypes_.OPTION);
        data.setListSelect(list);

        systemIntent.setData(data);

        Data responseData= new Data();
        responseData.setGoogle(new Google(systemIntent));

        response.setData(responseData);
        return response;
    }


    public void setContext(String name, int lifespan, Object parameters) {

    }

    public Context getContexts() {
        return null;
    }

    public Context getContext(String name) {
        return null;
    }

    public String getRawInput() {
        return null;
    }

    //setContext getContexts getContext getRawInput


    // ---------------------------------------------------------------------------
    //                   Private Helpers
    // ---------------------------------------------------------------------------

    /**
     *
     * @param inputPrompt
     * @param expectUserResponse
     * @param noInputPrompts
     * @return {@link Response}
     */
    private Response  buildResponse(Object inputPrompt, boolean expectUserResponse, String [] noInputPrompts){
        if (inputPrompt instanceof String) {
            String textToSpeech = (String) inputPrompt;
            if (textToSpeech.isEmpty()) {
                return null;
            }

            Response response = new Response();

            response.setSpeech(textToSpeech);
            //TODO _action_on_google_
            response.setContextOut(new ArrayList<>());
            Data data = new Data();
            Google google = new Google();
            google.setExpectUserResponse(expectUserResponse);
            if (Util.isSsml(textToSpeech)) {
                google.setSsml(true);
                List<SimpleResponse> finalNoInputPrompts = new ArrayList<>();
                if (!Util.isNull(noInputPrompts)){
                    for (String prompt: noInputPrompts) {
                        finalNoInputPrompts.add(new SimpleResponse(null, prompt, null));
                    }
                }
                google.setNoInputPrompts(finalNoInputPrompts);
                google.setSsml(false);
            } else if (!Util.isNull(noInputPrompts)) {
                List<SimpleResponse> finalNoInputPrompts = new ArrayList<>();
                for (String prompt: noInputPrompts) {
                    finalNoInputPrompts.add(new SimpleResponse(prompt, null, null));
                }
                google.setNoInputPrompts(finalNoInputPrompts);
                google.setSsml(false);
            } else {
                google.setSsml(false);
                google.setNoInputPrompts(new ArrayList<>());
            }
            data.setGoogle(google);
            response.setData(data);

            return response;

        } else if (inputPrompt instanceof SimpleResponse) {
            SimpleResponse simpleResponse = (SimpleResponse) inputPrompt;
            RichResponse richResponse = new RichResponse().addSimpleResponse(simpleResponse);
            return buildResponse(richResponse, expectUserResponse, null);

        } else if (inputPrompt instanceof RichResponse) {
            RichResponse richResponse = (RichResponse) inputPrompt;
            Response response = new Response();

            response.setSpeech(richResponse.getItems().get(0).getSimpleResponse().getTextToSpeech());
            response.setContextOut(new ArrayList<>());
            Data data = new Data();
            Google google = new Google();

            google.setExpectUserResponse(expectUserResponse);
            google.setRichResponse(richResponse);

            data.setGoogle(google);
            response.setData(data);
            return response;
        }

        return null;
    }

    /**
     *
     * @param systemIntentData
     * @return {@link Response}
     */
    @Override
    Response fulfillPermissionsRequest_(SystemIntentData systemIntentData) {
        Response response = new Response();
        Data data = new Data();
        Google google = new Google();
        SystemIntent systemIntent = new SystemIntent();

        final String inputPrompt = "PLACEHOLDER_FOR_PERMISSION";
        //TODO _buildResponse(inputPrompt, true)
        response.setSpeech(inputPrompt);
        google.setExpectUserResponse(true);
        google.setSsml(false);
        google.setNoInputPrompts(new ArrayList<>());

        systemIntent.setIntent("actions.intent.PERMISSION");
        systemIntentData.setType("type.googleapis.com/google.actions.v2.PermissionValueSpec");
        systemIntent.setData(systemIntentData);

        google.setSystemIntent(systemIntent);
        data.setGoogle(google);
        response.setData(data);
        return response;
    }
}
