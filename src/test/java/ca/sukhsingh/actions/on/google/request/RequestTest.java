package ca.sukhsingh.actions.on.google.request;

import ca.sukhsingh.actions.on.google.request.originalrequest.Argument;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RequestTest {

    @Test
    public void test() throws Exception {
        Request request = prepareRequest();
        assertNotNull(request);
        assertEquals("en-US", request.getUserLocale());
        assertEquals("1", ((Argument)request.getArgument("is_health_check")).getTextValue());
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

    private Request prepareRequest() {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("request.json").getFile());
        Request request = null;
        try {
            request = mapper.readValue(file, Request.class);
            assertNotNull(request);
        } catch (IOException e) {

        }
        return request;
    }
}
