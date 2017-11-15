package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.request.Request;
import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.Suggestion;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.Carousel;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.ListSelect;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by sukhsingh on 2017-09-11.
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

    protected void assertTextToSpeech(RichResponse response, String speech) {
        assertEquals("Text to speech", speech, response.getItems().get(0).getSimpleResponse().getTextToSpeech());
    }

    protected void assertDisplayText(Response response, String displayText) {
        assertEquals("Display Text",displayText, response.getData().getGoogle().getRichResponse().getItems().get(0).getSimpleResponse().getDisplayText());
    }

    protected void assertDisplayText(RichResponse response, String displayText) {
        assertEquals("Display Text",displayText, response.getItems().get(0).getSimpleResponse().getDisplayText());
    }

    protected void assertSsmlText(Response response, String ssmlText) {
        assertEquals("SSML Text",ssmlText, response.getData().getGoogle().getRichResponse().getItems().get(0).getSimpleResponse().getSsml());
    }

    protected void assertSsmlText(RichResponse response, String ssmlText) {
        assertEquals("SSML Text",ssmlText, response.getItems().get(0).getSimpleResponse().getSsml());
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

    protected void assertPermissionIntent(Response response) {
        assertEquals("System Intent : PERMISSION",AssistantApp.StandardIntents.PERMISSION, response.getData().getGoogle().getSystemIntent().getIntent());
    }

    protected void assertConfirmationIntent(Response response) {
        assertEquals("System Intent : CONFIRMATION",AssistantApp.StandardIntents.CONFIRMATION, response.getData().getGoogle().getSystemIntent().getIntent());
    }

    protected void assertOptionSystemIntentData(Response response) {
        assertEquals("System Intent Data type", AssistantApp.InputValueDataTypes.OPTION, response.getData().getGoogle().getSystemIntent().getData().getType());
    }

    protected void assertPermissionSystemIntentData(Response response) {
        assertEquals("System Intent Data type", AssistantApp.InputValueDataTypes.PERMISSION, response.getData().getGoogle().getSystemIntent().getData().getType());
    }

    protected void assertConfirmationSystemIntentData(Response response) {
        assertEquals("System Intent Data type", AssistantApp.InputValueDataTypes.CONFIRMATION, response.getData().getGoogle().getSystemIntent().getData().getType());
    }

    protected void assertListSelect(Response response, ListSelect listSelect) {

        ListSelect responseList = response.getData().getGoogle().getSystemIntent().getData().getListSelect();
        assertNotNull(responseList);
        assertNotNull(responseList);
        assertEquals("ListSelect Select title", listSelect.getTitle(), responseList.getTitle());
        for (int i=0; i<responseList.getItems().size(); i++) {
            assertEquals("ListSelect Select Item title", listSelect.getItems().get(i).getTitle(), responseList.getItems().get(i).getTitle());
            assertEquals("ListSelect Select Item Description", listSelect.getItems().get(i).getDescription(), responseList.getItems().get(i).getDescription());
            assertEquals("ListSelect Select Item Image url", listSelect.getItems().get(i).getImage().getUrl(), responseList.getItems().get(i).getImage().getUrl());
            assertEquals("ListSelect Select Item Image AccessibilityText", listSelect.getItems().get(i).getImage().getAccessibilityText(), responseList.getItems().get(i).getImage().getAccessibilityText());
            assertEquals("ListSelect Select Item OptionInfo key", listSelect.getItems().get(i).getOptionInfo().getKey(), responseList.getItems().get(i).getOptionInfo().getKey());
            assertEquals("ListSelect Select Item OptionInfo Synonyms", listSelect.getItems().get(i).getOptionInfo().getSynonyms(), responseList.getItems().get(i).getOptionInfo().getSynonyms());
        }
    }

    protected void assertCarousel(Response response, Carousel carousel) {

        Carousel responseCarousel = response.getData().getGoogle().getSystemIntent().getData().getCarousel();

        assertNotNull(responseCarousel);
        for (int i=0; i<responseCarousel.getItems().size(); i++) {
            assertEquals("Carousel Item title", carousel.getItems().get(i).getTitle(), responseCarousel.getItems().get(i).getTitle());
            assertEquals("Carousel Item Description", carousel.getItems().get(i).getDescription(), responseCarousel.getItems().get(i).getDescription());
            assertEquals("Carousel Item Image url", carousel.getItems().get(i).getImage().getUrl(), responseCarousel.getItems().get(i).getImage().getUrl());
            assertEquals("Carousel Item Image AccessibilityText", carousel.getItems().get(i).getImage().getAccessibilityText(), responseCarousel.getItems().get(i).getImage().getAccessibilityText());
            assertEquals("Carousel Item OptionInfo key", carousel.getItems().get(i).getOptionInfo().getKey(), responseCarousel.getItems().get(i).getOptionInfo().getKey());
            assertEquals("Carousel Item OptionInfo Synonyms", carousel.getItems().get(i).getOptionInfo().getSynonyms(), responseCarousel.getItems().get(i).getOptionInfo().getSynonyms());
        }
    }

    protected void assertNoInputPromptTexttoSpeech(Response response, String [] noInputPrompt) {
        assertEquals(response.getData().getGoogle().getNoInputPrompts().get(0).getTextToSpeech(),noInputPrompt[0]);
        assertEquals(response.getData().getGoogle().getNoInputPrompts().get(1).getTextToSpeech(),noInputPrompt[1]);
    }

    protected void assertInitialPrompt(String initialPrompt, Response response) {
        Assert.assertEquals(initialPrompt,response.getData().getGoogle().getSystemIntent().getData().getDialogSpec().getRequestDatetimeText());
    }

    protected void assertDatePrompt(String datePrompt, Response response) {
        Assert.assertEquals(datePrompt, response.getData().getGoogle().getSystemIntent().getData().getDialogSpec().getRequestDateText());
    }

    protected void assertTimePrompt(String timePrompt, Response response) {
        Assert.assertEquals(timePrompt, response.getData().getGoogle().getSystemIntent().getData().getDialogSpec().getRequestTimeText());
    }

    protected void assertDateTimeSystemIntent(Response response) {
        assertEquals(DialogflowApp.StandardIntents.DATETIME,response.getData().getGoogle().getSystemIntent().getIntent());
        assertEquals(DialogflowApp.InputValueDataTypes.DATETIME,response.getData().getGoogle().getSystemIntent().getData().getType());
    }

    protected void assertSurfaceSystemIntent(Response response) {
        assertEquals(DialogflowApp.StandardIntents.NEW_SURFACE,response.getData().getGoogle().getSystemIntent().getIntent());
        assertEquals(DialogflowApp.InputValueDataTypes.NEW_SURFACE,response.getData().getGoogle().getSystemIntent().getData().getType());
    }

    protected void assertSurfaceContext(Response response) {
        assertEquals("context", response.getData().getGoogle().getSystemIntent().getData().getContext());
    }

    protected void assertNotificationTitle(Response response) {
        assertEquals("notification", response.getData().getGoogle().getSystemIntent().getData().getNotificationTitle());
    }

    protected void assertSurfaceCapability(Response response) {
        assertEquals(Request.SurfaceCapabilities.SCREEN_OUTPUT, response.getData().getGoogle().getSystemIntent().getData().getCapabilities().get(0));
    }

}
