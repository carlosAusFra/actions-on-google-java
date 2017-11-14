package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AskForDateTimeTest {
    @InjectMocks
    private DialogflowApp app;

    @Test
    public void askForDateTimeWithAllNull() throws Exception {
        Response response = app.askForDateTime(null,null,null,null);
        Assert.assertNotNull(response);
    }

    @Test
    public void askForDateTimeWithInputPrompt() throws Exception {
        Response response = app.askForDateTime("Hello",null,null,null);
        Assert.assertNotNull(response);
    }

    @Test
    public void askForDateTimeWithDatePrompt() throws Exception {
        Response response = app.askForDateTime(null,"Hello",null,null);
        Assert.assertNotNull(response);
    }

    @Test
    public void askForDateTimeWithTimePrompt() throws Exception {
        Response response = app.askForDateTime(null,null,"Hello",null);
        Assert.assertNotNull(response);
    }

    @Test
    public void askForDateTimeWithAllText() throws Exception {
        Response response = app.askForDateTime("initialPrompt","datePrompt","Hello",null);
        Assert.assertNotNull(response);
    }
}
