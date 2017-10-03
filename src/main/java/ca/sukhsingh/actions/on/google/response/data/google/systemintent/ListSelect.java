package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static ca.sukhsingh.actions.on.google.Util.*;

/**
 * Created by sukhsingh on 2017-09-03.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "items"
})
public class ListSelect {

    Logger logger = Logger.getLogger(ListSelect.class);

    @JsonProperty("title")
    private String title;

    @JsonProperty("items")
    private List<Item> items = null;

    public ListSelect(Object list) {
        this.items = new ArrayList<>();
        if (isNotNull(list)) {
            if (list instanceof String) {
                this.title = (String) list;
            } else if (list instanceof ArrayList) {
                this.items = (ArrayList)list;
            } else if (list instanceof ListSelect) {
                //TODO null check
                this.title = ((ListSelect) list).title;
                //TODO items remaining
            }
        }
    }

    public ListSelect() {
    }

    public ListSelect setTitle(String title) {
        if (isNullOrEmpty(title)) {
            logger.error("title cannot be empty");
        }
        this.title = title;
        return this;
    }

    public ListSelect setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public ListSelect addItems(Object optionItems) {
        if (isNull(optionItems)) {
            logger.error("optionItems cannot be empty");
        } else if (optionItems instanceof ArrayList) {
            for (Item item:  (ArrayList<Item>)optionItems) {
                this.items.add(new Item(item));
            }
        } else {
            this.items.add((Item) optionItems);
        }

        // TODO  if (this.items.length > LIST_ITEM_LIMIT) {

        return this;
    }

    public String getTitle() {
        return title;
    }

    public List<Item> getItems() {
        return items;
    }
}
