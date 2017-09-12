package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import static ca.sukhsingh.actions.on.google.Util.*;

/**
 * Created by sukhsingh on 2017-09-03.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "items"
})
public class List {

    Logger logger = Logger.getLogger(List.class);

    @JsonProperty("title")
    private String title;

    @JsonProperty("items")
    private java.util.List<Item> items = null;

    public List(Object list) {
        this.items = new ArrayList<>();
        if (isNotNull(list)) {
            if (list instanceof String) {
                this.title = (String) list;
            } else if (list instanceof ArrayList) {
                this.items = (ArrayList)list;
            } else if (list instanceof List) {
                //TODO null check
                this.title = ((List) list).title;
                //TODO items remaining
            }
        }
    }

    public List() {
    }

    public List setTitle(String title) {
        if (isNullOrEmpty(title)) {
            logger.error("title cannot be empty");
        }
        this.title = title;
        return this;
    }

    public List setItems(java.util.List<Item> items) {
        this.items = items;
        return this;
    }

    public List addItems(Object optionItems) {
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

    public java.util.List<Item> getItems() {
        return items;
    }
}
