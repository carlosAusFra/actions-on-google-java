package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

/**
 * Created by sukhsingh on 2017-08-26.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static ca.sukhsingh.actions.on.google.Util.*;

/**
 * Class for initializing and constructing Basic Cards with chainable interface.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicCard {

    Logger logger = Logger.getLogger(BasicCard.class);

    @JsonProperty("title")
    private String title;
    @JsonProperty("subtitle")
    private String subtitle;
    @JsonProperty("formattedText")
    private String formattedText;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("buttons")
    private List<Button> buttons;

    public BasicCard() {
        this.title = null;
        this.formattedText = "";
        this.subtitle = null;
        this.image = new BasicCard.Image();
        this.buttons = new ArrayList();
    }

    /**
     * Constructor for BasicCard. Accepts optional BasicCard to clone.
     *
     * @param basicCard {@link BasicCard}
     */
    public BasicCard(BasicCard basicCard) {
        this.title = null;
        this.formattedText = "";
        this.subtitle = null;
        this.image = new Image();
        this.buttons = new ArrayList<Button>();

        if (basicCard != null) {
            if (basicCard.formattedText != null) {
                this.formattedText = basicCard.formattedText;
            }
            if (basicCard.buttons != null) {
                this.buttons = basicCard.buttons;
            }
            if (basicCard.title != null) {
                this.title = basicCard.title;
            }
            if (basicCard.subtitle != null) {
                this.subtitle = basicCard.subtitle;
            }
            if (basicCard.image != null) {
                this.image = basicCard.image;
            }
        }

    }

    /**
     * Sets the title for this Basic Card.
     *
     * @param title {@link String} title Title to show on card.
     * @return BasicCard Returns current constructed BasicCard.
     */
    public BasicCard setTitle (String title) {
        if (isNullOrEmpty(title)) {
            logger.error("Title can not be empty");
            return this;
        }
        this.title = title;
        return this;
    }

    /**
     * Sets the subtitle for this Basic Card.
     *
     * @param subtitle {@link String} subtitle Subtitle to show on card.
     * @return BasicCard Returns current constructed BasicCard.
     */
    public BasicCard setSubtitle (String subtitle) {
        if (isNullOrEmpty(subtitle)) {
            logger.error("subtitle can not be empty");
            return this;
        }
        this.subtitle = subtitle;
        return this;
    }

    /**
     * Sets the body text for this Basic Card.
     *
     * @param bodyText {@link String} bodyText Body text to show on card.
     * @return BasicCard Returns current constructed BasicCard.
     */
    public BasicCard setBodyText (String bodyText) {
        if (isNullOrEmpty(bodyText)) {
            logger.error("bodyText can not be empty");
            return this;
        }
        this.formattedText = bodyText;
        return this;
    }

    /**
     * Sets the image for this Basic Card.
     *
     * @param url {@link String} url Image source URL.
     * @param accessibilityText {@link String} accessibilityText Text to replace for image for
     *     accessibility.
     * @param width int width Width of the image.
     * @param height height Height of the image.
     * @return {BasicCard} Returns current constructed BasicCard.
     */
    public BasicCard setImage (String url, String accessibilityText, int width, int height) {
        if (isNullOrEmpty(url)) {
            logger.error("url cannot be empty or null");
            return this;
        }
        this.image.url = url;
        if (isNullOrEmpty(accessibilityText)){
            logger.error("accessibilityText cannot be empty or null");
            return this;
        }
        this.image.accessibilityText = accessibilityText;

        if (isNotNull(width)) {
            this.image.width = width;
        }
        if (isNotNull(height)) {
            this.image.height = height;
        }

        return this;
    }

    /**
     * Adds a button below card.
     *
     * @param text {@link String} text Text to show on button.
     * @param url {@link String} url URL to open when button is selected.
     * @return {BasicCard} Returns current constructed BasicCard.
     */
    public BasicCard addButton (String text, String url) {
        if (isNullOrEmpty(text)) {
            logger.error("text cannot be empty");
            return this;
        }
        if (isNullOrEmpty(url)) {
            logger.error("url cannot be empty");
            return this;
        }
        this.buttons.add(new Button(text, new OpenUrlAction(url)));
        return this;
    }

    public class Button {

        public Button(String title, OpenUrlAction openUrlAction) {
            this.title = title;
            this.openUrlAction = openUrlAction;
        }

        @JsonProperty("title")
        private String title;

        @JsonProperty("openUrlAction")
        private OpenUrlAction openUrlAction;
    }

    public class Image {
        @JsonProperty("url")
        private String url;
        @JsonProperty("accessibilityText")
        private String accessibilityText;
        @JsonProperty("height")
        private Integer height;
        @JsonProperty("width")
        private Integer width;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setAccessibilityText(String accessibilityText) {
            this.accessibilityText = accessibilityText;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }
    }

    public class OpenUrlAction {
        @JsonProperty("url")
        private String url;

        public OpenUrlAction(String url) {
            this.url = url;
        }
    }
}