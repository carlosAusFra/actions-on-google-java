package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.Data;
import ca.sukhsingh.actions.on.google.response.data.google.Google;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.SimpleResponse;

import java.util.ArrayList;

/**
 * Created by sukhsingh on 2017-08-28.
 */
public class ApiAiApp extends AssistantApp{

    public Response tell(String textToSpeech) {
        if (textToSpeech.isEmpty()) {
            return null;
        }

        return buildResponse(textToSpeech, false);
    }

    public Response tell(String textToSpeech, String displayText) {
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText(displayText);
        if (Util.isSsml(textToSpeech)) {
            simpleResponse.setSsml(textToSpeech);
        } else {
            simpleResponse.setTextToSpeech(textToSpeech);
        }
        return buildResponse(simpleResponse, false);
    }

    public Response tell(SimpleResponse simpleResponse) {
        return buildResponse(new RichResponse().addSimpleResponse(simpleResponse), false);
    }

    public Response tell(RichResponse richResponse) {
        return buildResponse(richResponse, false);
    }

    public Response ask(String textToSpeech) {
        if (textToSpeech.isEmpty()) {
            return null;
        }

        return buildResponse(textToSpeech, true);
    }

    public Response ask(String textToSpeech, String displayText) {
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText(displayText);
        if (Util.isSsml(textToSpeech)) {
            simpleResponse.setSsml(textToSpeech);
        } else {
            simpleResponse.setTextToSpeech(textToSpeech);
        }
        simpleResponse.setTextToSpeech(textToSpeech);
        return buildResponse(simpleResponse, true);
    }

    public Response ask(SimpleResponse simpleResponse) {
        return buildResponse(new RichResponse().addSimpleResponse(simpleResponse), true);
    }

    public Response ask(RichResponse richResponse) {
        return buildResponse(richResponse, true);
    }


    private Response buildResponse(String textToSpeech, boolean expectUserResponse) {
        if (textToSpeech.isEmpty()) {
            return null;
        }

        Response response = new Response();

        response.setSpeech(textToSpeech);
        response.setContextOut(new ArrayList<>());
        Data data = new Data();
        Google google = new Google();
        google.setExpectUserResponse(false);
        google.setSsml(false);
        google.setNoInputPrompts(new ArrayList<>());
        data.setGoogle(google);
        response.setData(data);

        return response;

    }

    private Response buildResponse(SimpleResponse simpleResponse, boolean expectUserResponse) {
        RichResponse richResponse = new RichResponse().addSimpleResponse(simpleResponse);
        return buildResponse(richResponse, expectUserResponse);
    }

    private Response buildResponse(RichResponse richResponse, boolean expectUserResponse) {
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
}
