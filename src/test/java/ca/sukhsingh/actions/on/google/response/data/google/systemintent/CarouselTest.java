package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import ca.sukhsingh.actions.on.google.AssertHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sukhsingh on 2017-09-14.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarouselTest extends AssertHelper {
    @InjectMocks
    Carousel carousel;

    @Test
    public void carouselConstructorWithListOfItems() throws Exception {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setTitle("title");
        item.setDescription("description");
        item.setKey("key");
        item.setImage("url", "test", 0,0);
        items.add(item);
        carousel = new Carousel(items);

        assertNotNull(carousel);
        assertEquals("title", carousel.getItems().get(0).getTitle());
        assertEquals("description", carousel.getItems().get(0).getDescription());
        assertEquals("key", carousel.getItems().get(0).getOptionInfo().getKey());
        assertEquals("url", carousel.getItems().get(0).getImage().getUrl());
    }

    @Test
    public void carouselConstructorWithItem() throws Exception {
        Item item = new Item();
        item.setTitle("title");
        item.setDescription("description");
        item.setKey("key");
        item.setImage("url", "test", 0,0);
        carousel = new Carousel(item);

        assertNotNull(carousel);
        assertEquals("title", carousel.getItems().get(0).getTitle());
        assertEquals("description", carousel.getItems().get(0).getDescription());
        assertEquals("key", carousel.getItems().get(0).getOptionInfo().getKey());
        assertEquals("url", carousel.getItems().get(0).getImage().getUrl());
    }

    @Test
    public void addItemsNull() throws Exception {
        carousel.addItems(null);
        assertEquals("item size should be 0", 0, carousel.getItems().size());
    }

    @Test
    public void addItemsWithList() throws Exception {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setTitle("title");
        item.setDescription("description");
        item.setKey("key");
        item.setImage("url", "test", 0,0);
        items.add(item);
        carousel.addItems(items);

        assertNotNull(carousel);
        assertEquals("title", carousel.getItems().get(0).getTitle());
        assertEquals("description", carousel.getItems().get(0).getDescription());
        assertEquals("key", carousel.getItems().get(0).getOptionInfo().getKey());
        assertEquals("url", carousel.getItems().get(0).getImage().getUrl());
    }

    @Test
    public void addItemsWithItem() throws Exception {
        Item item = new Item();
        item.setTitle("title");
        item.setDescription("description");
        item.setKey("key");
        item.setImage("url", "test", 0,0);
        carousel.addItems(item);

        assertNotNull(carousel);
        assertEquals("title", carousel.getItems().get(0).getTitle());
        assertEquals("description", carousel.getItems().get(0).getDescription());
        assertEquals("key", carousel.getItems().get(0).getOptionInfo().getKey());
        assertEquals("url", carousel.getItems().get(0).getImage().getUrl());
    }
}
