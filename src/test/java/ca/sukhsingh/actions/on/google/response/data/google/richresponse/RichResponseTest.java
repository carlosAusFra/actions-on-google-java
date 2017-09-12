package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

import ca.sukhsingh.actions.on.google.AssertHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;

/**
 * Created by sukhsingh on 2017-09-06.
 */
@Ignore
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
    public void addSimpleResponseTestWithItemMoreThenTwo() throws Exception {}
    @Test
    public void addSimpleResponseTestWithNullParam() throws Exception {
        RichResponse response = richResponse.addSimpleResponse(null);
        assertNull(response);
    }
    @Test
    public void simpleResponseParamWithTextToSpeechString() {}
    @Test
    public void simpleResponseParamWithSSSMLString() {}
    @Test
    public void simpleResponseParamWithEmptyString() {}
    @Test
    public void simpleResponseParamWithNullParam() {}
    @Test
    public void simpleResponseObjWithTextToSpeech() {}
    @Test
    public void simpleResponseObjWithSSML() {}
    @Test
    public void simpleResponseObjWithBothEmptyParams() {}
    @Test
    public void simpleResponseObjWithOnlyOneEmptyParam() {}
    @Test
    public void simpleResponseObjWithBothNullParams() {}
    @Test
    public void addSimpleResponseTestWithInvalidObj() throws Exception {}
    
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
