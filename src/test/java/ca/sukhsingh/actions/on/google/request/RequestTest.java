package ca.sukhsingh.actions.on.google.request;

import ca.sukhsingh.actions.on.google.AssistantApp;
import ca.sukhsingh.actions.on.google.request.originalrequest.Argument;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class RequestTest {

    @Test
    public void test() throws Exception {
        Request request = prepareRequest("request.json");
        assertNotNull(request);
        assertEquals("en-US", request.getUserLocale());
        assertEquals("1", request.getArgument("is_health_check"));
        assertEquals(true, request.hasSurfaceCapability("actions.capability.AUDIO_OUTPUT"));
        assertEquals(false, request.hasSurfaceCapability(Request.SurfaceCapabilities.SCREEN_OUTPUT));
        assertEquals("actions.capability.AUDIO_OUTPUT", request.getSurfaceCapabilities().get(0).getName());
        //TODO isInSandbox
        assertEquals("26f666bf-2b48-4cc5-a912-dc078382eba1", request.getId());
        assertEquals("2017-08-21T02:52:36.808Z", request.getTimestamp());
        assertEquals("en", request.getLang());
        // TODO getResult getStatus getORIntent
        assertEquals("1503283956786", request.getSessionId());
        assertEquals("input.question", request.getIntent());
        assertEquals("userID", request.getUserId());
        assertEquals("input.question", request.getAction());

//        assertEquals("", request.getTimestamp());

    }

    /*
    1. get capability
    2. get inputType
    3. get arguments
    4. get conversation token
    5. get context
    6. get message
    7. get ContextParameters
    8. get ContextParameter
    9. get Result parameters
    10.get result parameter
     */

    /*
    getSurfaceCapabilities
        with null capabilities
    hasSurfaceCapability
        with null capabilities
        happyPath
     */

    @Test
    public void getSurfaceCapabilitiesWithNullCapability() throws Exception {
        Request request = prepareRequest("surfaceCapabilities/no_surface_capability.json");
        assertNull(request.getSurfaceCapabilities());
    }

    @Test
    public void hasSurfaceCapabilitiesWithNullCapability() throws Exception {
        Request request = prepareRequest("surfaceCapabilities/no_surface_capability.json");
        assertEquals(false, request.hasSurfaceCapability("actions.capability.AUDIO_OUTPUT"));
    }

    @Test
    public void hasSurfaceCapabilitiesWithWrongCapability() throws Exception {
        Request request = prepareRequest("surfaceCapabilities/no_surface_capability.json");
        assertEquals(false, request.hasSurfaceCapability("actions.capability.SCREEN_OUTPUT"));
    }

    @Test
    public void hasSurfaceCapabilities() throws Exception {
        Request request = prepareRequest("request.json");
        assertEquals(true, request.hasSurfaceCapability("actions.capability.AUDIO_OUTPUT"));
    }

    /*
    getInputType
        with no inputType
        with UNSPECIFIED
        with TOUCH
        with VOICE
        with KEYBOARD
     */

    @Test
    public void getInputType() throws Exception {
        Request request = prepareRequest("getInputType/no_input_type.json");
        assertNull(request.getInputType());
    }

    @Test
    public void getInputTypeUnspecified() throws Exception {
        Request request = prepareRequest("getInputType/unspecified_input_type.json");
        assertEquals(AssistantApp.InputTypes.UNSPECIFIED, request.getInputType());
    }

    @Test
    public void getInputTypeTouch() throws Exception {
        Request request = prepareRequest("getInputType/touch_input_type.json");
        assertEquals(AssistantApp.InputTypes.TOUCH, request.getInputType());
    }

    @Test
    public void getInputTypeKeyboard() throws Exception {
        Request request = prepareRequest("getInputType/keyboard_input_type.json");
        assertEquals(AssistantApp.InputTypes.KEYBOARD, request.getInputType());
    }

    @Test
    public void getInputTypeVoice() throws Exception {
        Request request = prepareRequest("getInputType/voice_input_type.json");
        assertEquals(AssistantApp.InputTypes.VOICE, request.getInputType());
    }

    /*
    getArgument
        with empty param
        with null param
        with no incoming argument
        with textValue
        without textValue
     */

    @Test
    public void getArgumentWithEmptyParam() throws Exception {
        Request request = prepareRequest("getArgument/no_incoming_argument.json");
        assertNull(request.getArgument(""));
    }

    @Test
    public void getArgumentWithNullParam() throws Exception {
        Request request = prepareRequest("getArgument/no_incoming_argument.json");
        assertNull(request.getArgument(null));
    }

    @Test
    public void getArgumentWithNoIncomingArg() throws Exception {
        Request request = prepareRequest("getArgument/no_incoming_argument.json");
        assertNull(request.getArgument(null));
    }

    @Test
    public void getArgumentWithTextValue() throws Exception {
        Request request = prepareRequest("getArgument/with_textvalue_argument.json");
        assertEquals("repeat", request.getArgument("text"));
    }

    @Test
    public void getArgumentWithoutTextValue() throws Exception {
        Request request = prepareRequest("getArgument/without_textvalue_argument.json");
        assertEquals("repeat", ((Argument)request.getArgument("text")).getRawText());
    }

    /*
    getContext
        with null name
        with no context returned
        happy path

    getContextParameters
        with no parameters with contextName
        happyPath

    getContextParameter
        with no parameter with propertyName
        happypath

     */

    @Test
    public void getContextWithEmptyParam() throws Exception {
        Request request = prepareRequest("getContext/get_context.json");
        assertNull(request.getContext(""));
    }

    @Test
    public void getContextWithNullParam() throws Exception {
        Request request = prepareRequest("getContext/get_context.json");
        assertNull(request.getContext(null));
    }

    @Test
    public void getContextWithInvalidParam() throws Exception {
        Request request = prepareRequest("getContext/get_context.json");
        assertNull(request.getContext("test"));
    }

    @Test
    public void getContextHappyPath() throws Exception {
        Request request = prepareRequest("getContext/get_context.json");
        assertNotNull(request.getContext("question-followup"));
        assertEquals("question-followup", request.getContext("question-followup").getName());
    }

    @Test
    public void getContextParametersWithInvalidParam() throws Exception {
        Request request = prepareRequest("getContext/get_context.json");
        assertNull(request.getContextParameters("test"));
    }

    @Test
    public void getContextParameters() throws Exception {
        Request request = prepareRequest("getContext/get_context_parameters.json");
        assertEquals("Singh", request.getContextParameters("question-followup").get("sukh"));
    }

    @Test
    public void getContextParameterWithInvalidParam() throws Exception {
        Request request = prepareRequest("getContext/get_context.json");
        assertNull(request.getContextParameter("question-followup", "test"));
    }

    @Test
    public void getContextParameterWithInvalidParams() throws Exception {
        Request request = prepareRequest("getContext/get_context.json");
        assertNull(request.getContextParameter("question-followups", "test"));
    }

    @Test
    public void getContextParameter() throws Exception {
        Request request = prepareRequest("getContext/get_context_parameters.json");
        assertEquals("Singh", request.getContextParameter("question-followup", "sukh"));
    }

    private Request prepareRequest(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Request request = null;
        try {
            request = mapper.readValue(file, Request.class);
            assertNotNull(request);
        } catch (IOException e) {

        }
        return request;
    }
}
