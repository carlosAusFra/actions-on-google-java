
package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import static ca.sukhsingh.actions.on.google.Util.isNotNull;
import static ca.sukhsingh.actions.on.google.Util.isNull;
import static ca.sukhsingh.actions.on.google.Util.isNullOrEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "optionInfo",
    "title",
    "description",
    "image"
})
public class Item {

    Logger logger = Logger.getLogger(Item.class);

    @JsonProperty("optionInfo")
    private OptionInfo optionInfo;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image")
    private Image image;

    public Item(Item optionItem) {
        optionInfo = new OptionInfo();

        if (isNotNull(optionItem)) {
            if (isNotNull(optionItem.optionInfo)) {
                if (isNotNull(optionItem.optionInfo.getKey())) {
                    this.optionInfo.setKey(optionItem.optionInfo.getKey());
                }
                if (isNotNull(optionItem.optionInfo.getSynonyms())) {
                    this.optionInfo.setSynonyms(optionItem.optionInfo.getSynonyms());
                }
            }
            if (isNotNull(optionItem.title)) {
                this.title = optionItem.title;
            }
            if (isNotNull(optionItem.image)) {
                this.image = optionItem.image;
            }
            if (isNotNull(optionItem.description)) {
                this.description = optionItem.description;
            }
        }
    }

    public Item() {
    }

    public Item setTitle(String title) {
        if (isNullOrEmpty(title)) {
            logger.error("title can not be null or empty");
            return this;
        }
        this.title = title;
        return this;
    }

    public Item setDescription(String description) {
        if (isNullOrEmpty(description)) {
            logger.error("description can not be null or empty");
            return this;
        }
        this.description = description;
        return this;
    }

    public Item setImage(String url, String accessibilityText, int width, int height) {
        if (isNullOrEmpty(url)) {
            logger.error("url cannot be empty or null");
            return this;
        }
        if (isNullOrEmpty(accessibilityText)){
            logger.error("accessibilityText cannot be empty or null");
            return this;
        }
        this.image = new Image(url, accessibilityText);
        //TODO width & height
        return this;
    }

    public Item setKey(String key) {
        if (isNullOrEmpty(key)) {
            logger.error("invalid key");
        }
        this.optionInfo = new OptionInfo();
        this.optionInfo.setKey(key);
        return this;
    }

    public Item setSynonyms(Object synonyms) {
        if (isNull(synonyms)) {
            logger.error("invalid synonyms");
            return this;
        }
        if (synonyms instanceof ArrayList) {
            for (String synonym : (ArrayList<String>)synonyms) {
                this.optionInfo.getSynonyms().add(synonym);
            }
        } else if (synonyms instanceof String) {
            this.optionInfo.getSynonyms().add((String)synonyms);
        } else if (synonyms instanceof String[]) {
            for (String synonym: (String[]) synonyms) {
                this.optionInfo.getSynonyms().add(synonym);
            }
        }
        return this;
    }
}

