package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.Data;
import ca.sukhsingh.actions.on.google.response.data.google.Google;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.SimpleResponse;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.Carousel;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.SystemIntent;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.SystemIntentData;
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

    /**
     * Tells the Assistant to render the speech response and close the mic.
     *
     * @param textToSpeech text to speech as string
     * @return {@link Response}
     */
    public Response tell(String textToSpeech) {
        if (Util.isNullOrEmpty(textToSpeech)) {
            logger.error("Invalid text to speech");
            return null;
        }

        return buildResponse(textToSpeech, false, null);
    }

    /**
     * Tells the Assistant to render the speech response and close the mic.
     *
     * @param textToSpeech text to speech as string
     * @param displayText display text as string
     * @return {@link Response}
     */
    public Response tell(String textToSpeech, String displayText) {
        if (Util.isNullOrEmpty(textToSpeech)) {
            logger.error("textToSpeech is null or empty");
            return null;
        }
        if (Util.isNullOrEmpty(displayText)) {
            logger.error("displayText is null or empty");
            return null;
        }
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
     * @param simpleResponse a {@link SimpleResponse} Object
     * @return {@link Response}
     */
    public Response tell(SimpleResponse simpleResponse) {
        if (Util.isNull(simpleResponse)) {
            logger.error("Invalid simple response");
            return null;
        }
        return buildResponse(new RichResponse().addSimpleResponse(simpleResponse), false, null);
    }

    /**
     * Tells the Assistant to render the speech response and close the mic.
     *
     * @param richResponse a {@link RichResponse} object
     * @return {@link Response}
     */
    public Response tell(RichResponse richResponse) {
        if (Util.isNull(richResponse)) {
            logger.error("Invalid rich response");
            return null;
        }
        return buildResponse(richResponse, false, null);
    }

    /**
     * Asks to collect the user's input.
     *
     * NOTE: Due to a bug, if you specify the no-input prompts,
     * the mic is closed after the 3rd prompt, so you should use the 3rd prompt
     * for a bye message until the bug is fixed.
     *
     * @param textToSpeech text to speech as string
     * @param noInputPrompts string array of no input prompts
     * @return {@link Response}
     */
    public Response ask(String textToSpeech, String [] noInputPrompts) {
        if (Util.isNullOrEmpty(textToSpeech)) {
            return null;
        }

        return buildResponse(textToSpeech, true, noInputPrompts);
    }

    /**
     * Asks to collect the user's input.
     *
     * @param textToSpeech text to speech as string
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
     * @param textToSpeech text to speech as string
     * @param displayText display text as string
     * @return {@link Response}
     */
    public Response ask(String textToSpeech, String displayText) {
        if (Util.isNullOrEmpty(textToSpeech) && Util.isNullOrEmpty(displayText)) {
            logger.error("TextToSpeech or displat text is null/empty");
            return null;
        }
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

    /**
     * Asks to collect the user's input.
     *
     * @param simpleResponse inputPrompt of {@link SimpleResponse} type
     * @return Response
     */
    public Response ask(SimpleResponse simpleResponse) {
        return buildResponse(new RichResponse().addSimpleResponse(simpleResponse), true, null);
    }

    /**
     * Asks to collect the user's input.
     *
     * @param inputPrompt inputPrompt of {@link RichResponse} type
     * @return {@link Response}
     */
    public Response ask(RichResponse inputPrompt) {
        return buildResponse(inputPrompt, true, null);
    }


    /**
     * Asks to collect the user's input with a list.
     *
     * @param inputPrompt {@link String}|{@link RichResponse}|{@link SimpleResponse} inputPrompt
     * @param list {@link ca.sukhsingh.actions.on.google.response.data.google.systemintent.List} list
     * @return {@link Response}
     */
    public Response askWithList(Object inputPrompt, ca.sukhsingh.actions.on.google.response.data.google.systemintent.List list) {
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
        data.setType(InputValueDataTypes.OPTION);
        data.setListSelect(list);
        systemIntent.setData(data);
        Data responseData = response.getData();
        responseData.getGoogle().setSystemIntent(systemIntent);
        response.setData(responseData);
        return response;
    }

    /**
     * Asks to collect the user's input with a carousel.
     *
     * @param inputPrompt {@link String}|{@link RichResponse}|{@link SimpleResponse}
     * @param carousel {@link Carousel} object
     * @return {@link Response}
     */
    public Response askWithCarousel(Object inputPrompt, Carousel carousel) {
        Response response = new Response();

        if (Util.isNull(inputPrompt)) {
            logger.error("Invalid inputpromt");
            return null;
        }

        if (Util.isNull(carousel)) {
            logger.error("Invalid carousel");
            return null;
        }

        if (carousel.getItems().size() < 2) {
            logger.error("List requires at least 2 items");
            return null;
        }

        response = buildResponse(inputPrompt, true, null);
        if (Util.isNull(response)) {
            logger.error("Error in building response");
            return null;
        }


        SystemIntent systemIntent = new SystemIntent();
        systemIntent.setIntent(StandardIntents.OPTION);

        //TODO if(this.isNotApiVersionOne_()) {
        SystemIntentData data = new SystemIntentData();
        data.setType(InputValueDataTypes.OPTION);
        data.setCarousel(carousel);
        systemIntent.setData(data);
        Data responseData = response.getData();
        responseData.getGoogle().setSystemIntent(systemIntent);
        response.setData(responseData);
        return response;
    }


    /**
     * Set a new context for the current intent.
     *
     * @param name {@link String} contextOut Name
     * @param lifespan int lifespan of context
     * @param parameters {@link Object} additional parameters
     */
//    public void setContext(String name, int lifespan, Object parameters) {
//
//    }

    // ---------------------------------------------------------------------------
    //                   Private Helpers
    // ---------------------------------------------------------------------------

    /**
     * Builds a response for API.AI to send back to the Assistant.
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
            Data data = new Data();
            Google google = new Google();

            response.setSpeech(textToSpeech);
            //TODO _action_on_google_
            response.addContextOuts(new ArrayList<>());
            google.setExpectUserResponse(expectUserResponse);

            if (Util.isSsml(textToSpeech)) {
                google.setSsml(true);
                List<SimpleResponse> finalNoInputPrompts = new ArrayList<>();
                if (!Util.isNull(noInputPrompts)){
                    for (String prompt: noInputPrompts) {
                        finalNoInputPrompts.add(new SimpleResponse(prompt, null));
                    }
                }
                google.setNoInputPrompts(finalNoInputPrompts);
            } else if (!Util.isNull(noInputPrompts)) {
                List<SimpleResponse> finalNoInputPrompts = new ArrayList<>();
                for (String prompt: noInputPrompts) {
                    finalNoInputPrompts.add(new SimpleResponse(prompt, null));
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
            response.addContextOuts(new ArrayList<>());
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
     * Uses a PermissionsValueSpec object to construct and send a
     * permissions request to the user.
     *
     * @param systemIntentData
     * @return {@link Response}
     */
    @Override
    Response fulfillPermissionsRequest(SystemIntentData systemIntentData) {
        Response response = new Response();
        Data data = new Data();
        Google google;
        SystemIntent systemIntent = new SystemIntent();

        final String inputPrompt = "PLACEHOLDER_FOR_PERMISSION";
        response = buildResponse(inputPrompt, true, null);
        response.setSpeech(inputPrompt);
        systemIntent.setIntent(StandardIntents.PERMISSION);
        systemIntentData.setType(InputValueDataTypes.PERMISSION);
        systemIntent.setData(systemIntentData);
        google = response.getData().getGoogle();
        google.setSystemIntent(systemIntent);
        data.setGoogle(google);
        response.setData(data);
        return response;
    }
}
