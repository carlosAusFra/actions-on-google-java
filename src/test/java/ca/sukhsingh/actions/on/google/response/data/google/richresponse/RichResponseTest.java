package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by sukhsingh on 2017-09-06.
 */
@RunWith(MockitoJUnitRunner.class)
public class RichResponseTest {

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
     */

    /*
    addSuggestions(object)
     */

    /*
    addSuggestions(string, string, ...)
     */

    /*
    addSuggestionLink
     */
}
