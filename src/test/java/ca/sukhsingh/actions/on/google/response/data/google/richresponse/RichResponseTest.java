package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

import ca.sukhsingh.actions.on.google.AssertHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by sukhsingh on 2017-09-06.
 */
@RunWith(MockitoJUnitRunner.class)
public class RichResponseTest extends AssertHelper {

    @InjectMocks
    private RichResponse richResponse;

    /*
    addSimpleResponse(Object)
        1. Item count is more then 2
        2. null object
        3. param as string
            a. textToSpeech String
            b. ssml string
            c. empty string
            d. null
        4. param as SimpleResponse
            a. SimpleResponse with textToSpeech
            b. SimpleResponse with SSML
            c. SimpleResponse with both empty strings
            d. SimpleResponse with one empty string
            e. SimpleResponse with both null string
        5. param as int/or any other object
     */

    @Test
    public void addSimpleResponseTestWithItemMoreThenTwo() throws Exception {
        RichResponse response = new RichResponse();
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(new SimpleResponse("Hello", "hi")));
        itemList.add(new Item(new SimpleResponse("hello","hi")));
        response.setItems(itemList);
        RichResponse richResponse = new RichResponse(response);
        richResponse.addSimpleResponse(new SimpleResponse("sukh", "singh"));

        assertEquals("Item size should be 2", 2, richResponse.getItems().size());
        assertEquals("Items should be exactly same as itemList", itemList, richResponse.getItems());
    }
    @Test
    public void addSimpleResponseTestWithNullParam() throws Exception {
        RichResponse response = richResponse.addSimpleResponse(null);
        assertNull(response);
    }

    @Test
    public void simpleResponseParamWithTextToSpeechString() {
        richResponse.addSimpleResponse("Hello");
        assertTextToSpeech(richResponse, "Hello");
    }

    @Test
    public void simpleResponseParamWithSSSMLString() {
        final String SSML = "<speak>Hello</speak>";
        richResponse.addSimpleResponse(SSML);
        assertSsmlText(richResponse, SSML);
    }

//    public void simpleResponseParamWithEmptyString() {
//
//    }

    @Test
    public void simpleResponseParamWithNullParam() {
        richResponse.addSimpleResponse(null);
        assertEquals("Rich response should not have any item",0,richResponse.getItems().size());
    }
    @Test
    public void simpleResponseObjWithTextToSpeech() {
        richResponse.addSimpleResponse(new SimpleResponse("Hello", "hi"));
        assertTextToSpeech(richResponse,"Hello");
        assertDisplayText(richResponse, "hi");
    }

    @Test
    public void simpleResponseObjWithSSML() {
        richResponse.addSimpleResponse(new SimpleResponse("<speak>Hello</speak>", "hi"));
        assertSsmlText(richResponse,"<speak>Hello</speak>");
        assertDisplayText(richResponse, "hi");
    }

    @Test
    public void addSimpleResponseTestWithInvalidObj() throws Exception {
        richResponse.addSimpleResponse(new Object());
        assertNull(richResponse.getItems().get(0).getSimpleResponse());
    }
    
    /*
    addSimpleResponse(string, string)
        1. Item count is more then 2
        2. Both strings null
        3. Both strings empty
        4. One string empty
        param 1 as textToSpeech string
        param 1 as SSML string
     */

    @Test
    public void addSimpleResponseWithBothStringsNull() throws Exception {
        richResponse.addSimpleResponse(null,null);
        assertEquals("Item size should be 0",0,richResponse.getItems().size());
    }

    @Test
    public void addSimpleResponseWithBothStringsEmpty() throws Exception {
        richResponse.addSimpleResponse("","");
        assertEquals("Item size should be 0",0,richResponse.getItems().size());
    }

    @Test
    public void addSimpleResponseWithOneStringNull() throws Exception {
        richResponse.addSimpleResponse("Hello",null);
        assertEquals("Item size should be 0",0,richResponse.getItems().size());
    }

    @Test
    public void addSimpleResponseWithOneStringEmpty() throws Exception {
        richResponse.addSimpleResponse("Hello","");
        assertEquals("Item size should be 0",0,richResponse.getItems().size());
    }

    @Test
    public void addSimpleResponseWithTextToSpeech() throws Exception {
        richResponse.addSimpleResponse("Hello","Hi");
        assertTextToSpeech(richResponse, "Hello");
        assertDisplayText(richResponse, "Hi");
    }

    @Test
    public void addSimpleResponseWithSSML() throws Exception {
        richResponse.addSimpleResponse("<speak>Hello</speak>","Hi");
        assertSsmlText(richResponse, "<speak>Hello</speak>");
        assertDisplayText(richResponse, "Hi");
    }

    /*
    addBasicCard
        1. basic card null
        2. More then one basic card
        3. HappyPath
     */

    @Test
    public void addBasicCardWithNullParam() throws Exception {
        richResponse.addBasicCard(null);
        assertEquals("Item size should be 0",0,richResponse.getItems().size());
    }

    @Test
    public void addBasicCard() throws Exception {
        richResponse.addBasicCard(new BasicCard()
                .setTitle("Title")
                .setBodyText("BodyText")
                .setSubtitle("Subtitle")
                .setImage("url", "accessibility", 9, 6)
                .addButton("Visit", "url"));

        assertEquals("Basic card title", "Title", richResponse.getItems().get(0).getBasicCard().getTitle());
        assertEquals("Basic card BodyText", "BodyText", richResponse.getItems().get(0).getBasicCard().getFormattedText());
        assertEquals("Basic card Subtitle", "Subtitle", richResponse.getItems().get(0).getBasicCard().getSubtitle());
        assertEquals("Basic card Image Url", "url", richResponse.getItems().get(0).getBasicCard().getImage().getUrl());
        assertEquals("Basic card Image accessibility", "accessibility", richResponse.getItems().get(0).getBasicCard().getImage().getAccessibilityText());
    }

    @Test
    public void addTwoBasicCard() throws Exception {
        richResponse.addBasicCard(new BasicCard()
                .setTitle("Title")
                .setBodyText("BodyText")
                .setSubtitle("Subtitle")
                .setImage("url", "accessibility", 9, 6)
                .addButton("Visit", "url"));

        richResponse.addBasicCard(new BasicCard()
                .setTitle("Title2")
                .setBodyText("BodyText2")
                .setSubtitle("Subtitle2")
                .setImage("url2", "accessibility2", 9, 6)
                .addButton("Visit2", "url2"));

        assertEquals("Item size should be 1",1, richResponse.getItems().size());
        assertEquals("Basic card title", "Title", richResponse.getItems().get(0).getBasicCard().getTitle());
        assertEquals("Basic card BodyText", "BodyText", richResponse.getItems().get(0).getBasicCard().getFormattedText());
        assertEquals("Basic card Subtitle", "Subtitle", richResponse.getItems().get(0).getBasicCard().getSubtitle());
        assertEquals("Basic card Image Url", "url", richResponse.getItems().get(0).getBasicCard().getImage().getUrl());
        assertEquals("Basic card Image accessibility", "accessibility", richResponse.getItems().get(0).getBasicCard().getImage().getAccessibilityText());
    }

    /*
    addSuggestions(object)
        1. object null
        2. object os ArrayList
        3. object is List
            a. List<String>
            b. List<Suggestion>
            c. Invalid list
        4. object as String[]
        5. object as String
        6. object as invalid type
     */

    @Test
    public void addSuggestionsWithNullParam() throws Exception {
        assertNull(richResponse.addSuggestions(null));
    }

    @Test
    public void addSuggestionsWithStringArrayListParam () throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("test");
        strings.add("test2");
        richResponse.addSuggestions(strings);
        assertEquals("test", richResponse.getSuggestions().get(0).getTitle());
        assertEquals("test2", richResponse.getSuggestions().get(1).getTitle());
    }

    @Test
    public void addSuggestionsWithSuggestionArrayListParam() throws Exception {
        ArrayList<Suggestion> suggestions = new ArrayList<>();
        suggestions.add(new Suggestion("test1"));
        suggestions.add(new Suggestion("test2"));
        richResponse.addSuggestions(suggestions);
        assertEquals("test1", richResponse.getSuggestions().get(0).getTitle());
        assertEquals("test2", richResponse.getSuggestions().get(1).getTitle());
    }

    @Test
    public void addSuggestionsWithInvalidArrayListParam() throws Exception {
        ArrayList<Object> suggestions = new ArrayList<>();
        suggestions.add(1);
        suggestions.add(1);
        richResponse.addSuggestions(suggestions);
        assertEquals(0,richResponse.getSuggestions().size());
    }

    @Test
    public void addSuggestionsWithStringListParam () throws Exception {
        richResponse.addSuggestions(Arrays.asList("test1", "test2"));
        assertEquals("test1", richResponse.getSuggestions().get(0).getTitle());
        assertEquals("test2", richResponse.getSuggestions().get(1).getTitle());
    }

    @Test
    public void addSuggestionsWithSuggestionListParam() throws Exception {
        Suggestion suggestion = new Suggestion("test");
        richResponse.addSuggestions(Arrays.asList(suggestion));
        assertEquals("test", richResponse.getSuggestions().get(0).getTitle());
    }

    @Test
    public void addSuggestionsWithInvalidListParam() throws Exception {
        richResponse.addSuggestions(Arrays.asList(1,2,3));
        assertEquals(0,richResponse.getSuggestions().size());
    }

    @Test
    public void addSuggestionWithInvalidParam() throws Exception {
        richResponse.addSuggestions(new SimpleResponse());
        assertEquals(0,richResponse.getSuggestions().size());
    }

    @Test
    public void addSuggestionsWithStringArrayParam () throws Exception {
        richResponse.addSuggestions(new String[] {"test1", "test2"});
        assertEquals("test1", richResponse.getSuggestions().get(0).getTitle());
        assertEquals("test2", richResponse.getSuggestions().get(1).getTitle());
    }

    @Test
    public void addSuggestionsWithStringParam () throws Exception {
        richResponse.addSuggestions("test1");
        assertEquals("test1", richResponse.getSuggestions().get(0).getTitle());
    }

    /*
    addSuggestions(string, string, ...)
        1. null param
        2. One string param
        3. More than one string params
     */

    @Test
    public void addSuggestions2WithNullParam() throws Exception {
        String [] strings= null;
        assertNull(richResponse.addSuggestions(strings));
    }

    @Test
    public void addSuggestionsWithOneParam() throws Exception {
        String [] strings= {"test"};
        richResponse.addSuggestions(strings);
        assertEquals("test", richResponse.getSuggestions().get(0).getTitle());
    }

    @Test
    public void addSuggestionsWithMultipleStringParam () throws Exception {
        richResponse.addSuggestions("test1", "test2", "test3");
        assertEquals("test1", richResponse.getSuggestions().get(0).getTitle());
        assertEquals("test2", richResponse.getSuggestions().get(1).getTitle());
        assertEquals("test3", richResponse.getSuggestions().get(2).getTitle());
    }

    /*
    addSuggestionLink
        1. Null or empty params
     */

    @Test
    public void addSuggestionLinkNullParams() throws Exception {
        richResponse.addSuggestionLink(null, null);
        assertNull(richResponse.getLinkOutSuggestion().getDestinationName());
        assertNull(richResponse.getLinkOutSuggestion().getUrl());
    }

    @Test
    public void addSuggestionLinkNullURL() throws Exception {
        richResponse.addSuggestionLink("test", null);
        assertNull(richResponse.getLinkOutSuggestion().getDestinationName());
        assertNull(richResponse.getLinkOutSuggestion().getUrl());
    }

    @Test
    public void addSuggestionLinkEmptyParams() throws Exception {
        richResponse.addSuggestionLink("", "");
        assertNull(richResponse.getLinkOutSuggestion().getDestinationName());
        assertNull(richResponse.getLinkOutSuggestion().getUrl());
    }

    @Test
    public void addSuggestionLink() throws Exception {
        richResponse.addSuggestionLink("test", "test-url");
        assertEquals("test", richResponse.getLinkOutSuggestion().getDestinationName());
        assertEquals("test-url", richResponse.getLinkOutSuggestion().getUrl());
    }
}
