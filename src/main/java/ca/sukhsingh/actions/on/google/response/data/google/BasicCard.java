package ca.sukhsingh.actions.on.google.response.data.google;

/**
 * Created by sinsukhv on 2017-08-26.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for initializing and constructing Basic Cards with chainable interface.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicCard {

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

    /**
     * Constructor for BasicCard. Accepts optional BasicCard to clone.
     *
     * @param {BasicCard} basicCard
     */
    public BasicCard(BasicCard basicCard) {
        this.title = null;
        this.formattedText = "";
        this.subtitle = null;
        this.image = null;
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
     * @param {string} title Title to show on card.
     * @return {BasicCard} Returns current constructed BasicCard.
     */
    public BasicCard setTitle (String title) {
        //TODO check null or empty
        this.title = title;
        return this;
    }

    /**
     * Sets the subtitle for this Basic Card.
     *
     * @param {string} subtitle Subtitle to show on card.
     * @return {BasicCard} Returns current constructed BasicCard.
     */
    public BasicCard setSubtitle (String subtitle) {
        //TODO check null or empty
        this.subtitle = subtitle;
        return this;
    }

    /**
     * Sets the body text for this Basic Card.
     *
     * @param {string} bodyText Body text to show on card.
     * @return {BasicCard} Returns current constructed BasicCard.
     */
    public BasicCard setBodyText (String bodyText) {
        //TODO check null or empty
        this.formattedText = bodyText;
        return this;
    }

    /**
     * Sets the image for this Basic Card.
     *
     * @param {string} url Image source URL.
     * @param {string} accessibilityText Text to replace for image for
     *     accessibility.
     * @param {number=} width Width of the image.
     * @param {number=} height Height of the image.
     * @return {BasicCard} Returns current constructed BasicCard.
     */
    public BasicCard setImage (String url, String accessibilityText, int width, int height) {
        //TODO check null or empty for url and accessibilityText
        this.image.url = url;
        this.image.accessibilityText = accessibilityText;
        //TODO null check
        this.image.width = width;
        //TODO null check
        this.image.height = height;

        return this;
    }

    /**
     * Adds a button below card.
     *
     * @param {string} text Text to show on button.
     * @param {string} url URL to open when button is selected.
     * @return {BasicCard} Returns current constructed BasicCard.
     */
    public BasicCard addButton (String text, String url) {
        //TODO null check for text and url
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
    }

    public class OpenUrlAction {
        @JsonProperty("url")
        private String url;

        public OpenUrlAction(String url) {
            this.url = url;
        }
    }
}