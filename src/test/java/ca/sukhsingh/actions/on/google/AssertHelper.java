package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.Suggestion;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by sinsukhv on 2017-09-11.
 */
public class AssertHelper {

    protected void assertSpeech(Response response, String speech) {
        assertEquals("Speech test",speech, response.getSpeech());
    }

    protected void assertExpectUserResponseFalse(Response response) {
        assertEquals("Expect User Response False",false, response.getData().getGoogle().expectUserResponse);
    }
    protected void assertExpectUserResponseTrue(Response response) {
        assertEquals("Expect User Response True",true, response.getData().getGoogle().expectUserResponse);
    }

    protected void assertIsSsmlFalse(Response response) {
        assertEquals("Is Ssml False",false, response.getData().getGoogle().isSsml);
    }

    protected void assertIsSsmlTrue(Response response) {
        assertEquals("Is Ssml True",true, response.getData().getGoogle().isSsml);
    }

    protected void assertNotNullRichResponse(Response response) {
        assertNotNull(response.getData().getGoogle().getRichResponse());
        assertNotNull(response.getData().getGoogle().getRichResponse().getItems());
        assertNotNull(response.getData().getGoogle().getRichResponse().getItems().get(0).getSimpleResponse());
        assertNotNull(response.getData().getGoogle().getRichResponse().getSuggestions());

    }

    protected void assertTextToSpeech(Response response, String speech) {
        assertEquals("Text to speech", speech, response.getData().getGoogle().getRichResponse().getItems().get(0).getSimpleResponse().getTextToSpeech());
    }

    protected void assertDisplayText(Response response, String displayText) {
        assertEquals("Display Text",displayText, response.getData().getGoogle().getRichResponse().getItems().get(0).getSimpleResponse().getDisplayText());
    }

    protected void assertSsmlText(Response response, String ssmlText) {
        assertEquals("SSML Text",ssmlText, response.getData().getGoogle().getRichResponse().getItems().get(0).getSimpleResponse().getSsml());
    }

    protected void assertSuggestions(Response response, String ...suggestions) {
        List<Suggestion> suggestionList = response.getData().getGoogle().getRichResponse().getSuggestions();
        for (int i=0; i< suggestions.length; i++) {
            assertEquals("Suggestion Title", suggestions[i], suggestionList.get(i).getTitle());
        }
    }

    protected List<Suggestion> suggestions(String ... strings) {
        List<Suggestion> suggestions = new ArrayList<>();
        for (String suggestion : strings) {
            suggestions.add(new Suggestion(suggestion));
        }
        return suggestions;
    }

    protected void assertOptionIntent(Response response) {
        assertEquals("System Intent : OPTION",AssistantApp.StandardIntents.OPTION, response.getData().getGoogle().getSystemIntent().getIntent());
    }

    protected void assertOptionSystemIntentData(Response response) {
        assertEquals("System Intent Data type",AssistantApp.InputValueDataTypes_.OPTION, response.getData().getGoogle().getSystemIntent().getData().getType());
    }

    protected void assertListSelect(Response response, ca.sukhsingh.actions.on.google.response.data.google.systemintent.List list) {

        ca.sukhsingh.actions.on.google.response.data.google.systemintent.List responseList = response.getData().getGoogle().getSystemIntent().getData().getListSelect();
        assertNotNull(responseList);
        assertNotNull(responseList);
        assertEquals("List Select title", list.getTitle(), responseList.getTitle());
        for (int i=0; i<responseList.getItems().size(); i++) {
            assertEquals("List Select Item title", list.getItems().get(1).getTitle(), responseList.getItems().get(1).getTitle());
            assertEquals("List Select Item Description", list.getItems().get(1).getDescription(), responseList.getItems().get(1).getDescription());
            assertEquals("List Select Item Image url", list.getItems().get(1).getImage().getUrl(), responseList.getItems().get(1).getImage().getUrl());
            assertEquals("List Select Item Image AccessibilityText", list.getItems().get(1).getImage().getAccessibilityText(), responseList.getItems().get(1).getImage().getAccessibilityText());
            assertEquals("List Select Item OptionInfo key", list.getItems().get(1).getOptionInfo().getKey(), responseList.getItems().get(1).getOptionInfo().getKey());
            assertEquals("List Select Item OptionInfo Synonyms", list.getItems().get(1).getOptionInfo().getSynonyms(), responseList.getItems().get(1).getOptionInfo().getSynonyms());
        }
    }
}
