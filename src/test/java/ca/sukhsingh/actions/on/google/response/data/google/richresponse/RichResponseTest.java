package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

import ca.sukhsingh.actions.on.google.AssertHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
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

//    @Test
//    public void simpleResponseObjWithBothEmptyParams() {
//        richResponse.addSimpleResponse(new SimpleResponse("", ""));
//        assertEquals("Rich response should not have any item",0,richResponse.getItems().size());
//    }


//    public void simpleResponseObjWithOnlyOneEmptyParam() {}

//    public void simpleResponseObjWithBothNullParams() {}
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

    /*
    addBasicCard
        1. basic card null
        2.
     */

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

    /*
    addSuggestions(string, string, ...)
        1. null param
        2. One string param
        3. More than one string params
     */

    /*
    addSuggestionLink
        1. Null or empty params
     */
}
