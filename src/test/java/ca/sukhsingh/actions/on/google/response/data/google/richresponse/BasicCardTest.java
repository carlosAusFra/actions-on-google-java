package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

import ca.sukhsingh.actions.on.google.ApiAiApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by Sukhsingh on 2017-09-14.
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicCardTest {

    @InjectMocks
    ApiAiApp app;


    @Test
    public void basicCardConstructor() throws Exception {
        app.buildBasicCard("test");
        BasicCard basicCard = new BasicCard(app.buildBasicCard("test").
                setTitle("title").
                setSubtitle("subtitle").
                setImage("url", "test"));

        Assert.assertEquals("test", basicCard.getFormattedText());
        Assert.assertEquals("title", basicCard.getTitle());
        Assert.assertEquals("subtitle", basicCard.getSubtitle());
        Assert.assertEquals("url", basicCard.getImage().getUrl());
    }

    @Test
    public void setTitleNull() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.setTitle(null);
        Assert.assertNull(basicCard.getTitle());
    }

    @Test
    public void setBodyTextNull() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.setBodyText(null);
        Assert.assertNull(basicCard.getFormattedText());
    }

    @Test
    public void setSubtitleNull() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.setSubtitle(null);
        Assert.assertNull(basicCard.getSubtitle());
    }

    @Test
    public void setImageNullUrl() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.setImage(null, "test");
        Assert.assertNull(basicCard.getImage().getUrl());
    }

    @Test
    public void setImageNullAccessibility() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.setImage("test", null);
        Assert.assertNull(basicCard.getImage().getAccessibilityText());
    }

    @Test
    public void addButtonNullText() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.addButton(null, "test");
        Assert.assertEquals("Buttons size must be 0", 0, basicCard.getButtons().size());
    }

    @Test
    public void addButtonNullUrl() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.addButton("test", null);
        Assert.assertEquals("buttons size must be 0", 0, basicCard.getButtons().size());
    }

    @Test
    public void addButton() throws Exception {
        BasicCard basicCard = new BasicCard();
        basicCard.addButton("test","test-url");
        Assert.assertEquals("button text", "test", basicCard.getButtons().get(0).getTitle());
        Assert.assertEquals("button text", "test-url", basicCard.getButtons().get(0).getOpenUrlAction().getUrl());

    }
}
