
package ca.sukhsingh.actions.on.google.response.data.google.systemIntent;

import java.util.ArrayList;
import java.util.List;

import ca.sukhsingh.actions.on.google.Util;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.log4j.Logger;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "items"
})
public class Carousel {

    Logger logger = Logger.getLogger(Carousel.class);

    @JsonProperty("items")
    private List<Item> items;

    public Carousel() {
        items = new ArrayList<>();
    }

    /**
     * Constructor for Carousel. Accepts optional Carousel to clone or list of
     * items to copy.
     *
     * @param {Carousel|Array<OptionItem>} carousel Either a carousel to clone, a
     *     or an array of OptionItem to initialize a new carousel
     */
    public Carousel(Object carousel) {
        this.items = new ArrayList<>();

        if (Util.isNotNull(carousel)){
            if (carousel instanceof ArrayList) {
                for (Item item:(ArrayList<Item>) carousel) {
                    this.items.add(item);
                }
            } else if (carousel instanceof Item) {
                this.items.add((Item)carousel);
            }
        }
    }

    /**
     * Adds a single item or list of items to the carousel.
     *
     * @param {OptionItem|Array<OptionItem>} optionItems OptionItems to add.
     * @return {Carousel} Returns current constructed Carousel.
     */
    public Carousel addItems(Object optionItems) {
        if (Util.isNull(optionItems)) {
            logger.error("optionItems cannot be null");
            return this;
        }

        if (optionItems instanceof ArrayList) {
            for (Item item:(ArrayList<Item>) optionItems) {
                this.items.add(item);
            }
        } else if (optionItems instanceof Item) {
            this.items.add((Item)optionItems);
        }

        //TODO if (this.items.length > CAROUSEL_ITEM_LIMIT) {
        return this;
    }

    public List<Item> getItems() {
        return items;
    }
}
