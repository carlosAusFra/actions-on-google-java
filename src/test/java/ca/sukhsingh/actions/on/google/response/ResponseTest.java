package ca.sukhsingh.actions.on.google.response;

import ca.sukhsingh.actions.on.google.response.data.google.Data;
import ca.sukhsingh.actions.on.google.response.data.google.Google;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sukhsingh on 2017-09-14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponseTest {
    @Test
    public void responseConstructor() throws Exception {
        Response res = new Response();
        res.setSpeech("test");
        List<ContextOut> contextOuts = new ArrayList<>();
        contextOuts.add(new ContextOut("one", 1, new Parameters()));
        res.addContextOuts(contextOuts);
        res.setData(new Data());
        Response response = new Response(res);
        response.addContextOut(new ContextOut("two",2, null));

        assertEquals("test",response.getSpeech());
        assertEquals("one", response.getContextOut().get(0).getName());
        assertEquals("two", response.getContextOut().get(1).getName());

    }

    @Test
    public void responseConstructorWithData() throws Exception {
        Data data = new Data();
        Google google = new Google();
        google.setSsml(false);
        data.setGoogle(google);
        Response response = new Response(data);
        assertEquals(false, response.getData().getGoogle().isSsml);
    }
}
