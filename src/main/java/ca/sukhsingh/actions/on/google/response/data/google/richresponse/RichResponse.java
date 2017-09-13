package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

/**
 * Created by sukhsingh on 2017-08-26.
 */

import ca.sukhsingh.actions.on.google.Util;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RichResponse {

    Logger logger = Logger.getLogger(RichResponse.class);

    @JsonProperty("items")
    private List<Item> items ;

    @JsonProperty("suggestions")
    private List<Suggestion> suggestions ;

    @JsonProperty("linkOutSuggestion")
    private LinkOutSuggestion linkOutSuggestion;

    public List<Item> getItems() {
        return items;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    /**
     * @return {@link LinkOutSuggestion} get link out suggestion object
     */
    public LinkOutSuggestion getLinkOutSuggestion() {
        return linkOutSuggestion;
    }

    /**
     * Default constructor for RichResponse
     */
    public RichResponse() {
        this.items = new ArrayList<>();
        this.suggestions = new ArrayList<>();
        this.linkOutSuggestion = new LinkOutSuggestion();
    }

    /**
     * Constructor for RichResponse. Accepts RichResponse to clone.
     * @param richResponse returns RichResponse object
     */
    public RichResponse(RichResponse richResponse) {

        this.items = new ArrayList<>();
        this.suggestions = new ArrayList<>();
        this.linkOutSuggestion = new LinkOutSuggestion();

        if (richResponse != null) {
            if (richResponse.getItems() != null) {
                this.items = richResponse.items;
                for (Item item : items) {
                    if (item.getBasicCard() != null) {
                        item.setBasicCard(new BasicCard(item.getBasicCard()));
                    }
                }
            }
            if (richResponse.getSuggestions() != null) {
                this.suggestions = richResponse.suggestions;
            }
            if (richResponse.getLinkOutSuggestion() != null) {
                this.linkOutSuggestion = richResponse.linkOutSuggestion;
            }
        }
    }

    /**
     * Adds a SimpleResponse to list of items.
     *
     * @param simpleResponse param can be  string|SimpleResponse
     *                       Simple response to present to
     *                       user. If just a string, display text will not be set.
     * @return {@link RichResponse} Returns current constructed RichResponse.
     */
    public RichResponse addSimpleResponse(Object simpleResponse) {
        if(simpleResponse == null) {
            logger.error("Invalid SimpleResponse");
            return null;
        }

        int simpleResponseCount = 0;
        for (Item item : this.items) {
            if (item.getSimpleResponse() != null) {
                simpleResponseCount++;
            }
            if (simpleResponseCount >= 2) {
                logger.error("Cannot include >2 SimpleResponses in richresponse");
                return this;
            }
        }
        SimpleResponse simpleResponse_ = buildSimpleResponseHelper(simpleResponse);
        //TODO Check first if needs to replace BasicCard at beginning of items list
        this.items.add(new Item(simpleResponse_));
        return this;
    }

    /**
     * Adds a SimpleResponse to list of items.
     *
     * @param textOrSsml textOrSSML can be textToSpeech or SSML
     * @param displayText this method required displayText
     * @return {@link RichResponse} Returns current constructed RichResponse.
     */
    public RichResponse addSimpleResponse(String textOrSsml, String displayText) {
        if (Util.isNullOrEmpty(textOrSsml)) {
            logger.error("Invalid textOrSsml");
            return null;
        }

        if (Util.isNullOrEmpty(displayText)) {
            logger.error("Invalid displayText");
            return null;
        }

        SimpleResponse simpleResponse = new SimpleResponse(textOrSsml, displayText);
        //TODO Check first if needs to replace BasicCard at beginning of items list
        this.items.add(new Item(simpleResponse));
        return this;
    }

    /**
     * Adds a BasicCard to list of items.
     *
     * @param basicCard basicCard Basic card to include in response.
     * @return {@link RichResponse} Returns current constructed RichResponse.
     */
    public RichResponse addBasicCard(BasicCard basicCard) {
        
        if(basicCard == null) {
            logger.error("Invalid basicCard");
            return null;
        }

        for (Item item : this.items) {
            if (item.getBasicCard() != null) {
                logger.error("Cannot include >1 BasicCard in rich response");
                return this;
            }
        }

        this.items.add(new Item(basicCard));

        return this;
    }

    public RichResponse addSuggestions(Object suggestions) {

        if (suggestions == null) {
            logger.error("Invalid suggestions");
            return null;
        }
        if (suggestions instanceof ArrayList) {
            // check if List<String> or List<Suggestion>
            if (((ArrayList) suggestions).get(0) instanceof String) {

                ArrayList<String> suggestions_ = (ArrayList) suggestions;
                for (String suggestion : suggestions_) {
                    this.suggestions.add(new Suggestion(suggestion));
                }

            } else if (((ArrayList) suggestions).get(0) instanceof Suggestion) {

                this.suggestions = (ArrayList) suggestions;;
            } else {

                logger.error("Invalid ArrayList. ArrayList must be of either String or Suggestion");
            }

        } else if (suggestions instanceof List) {
            // check if List<String> or List<Suggestion>
            if (((List) suggestions).get(0) instanceof String) {

                List<String> suggestions_ = (List<String>) suggestions;
                for (String suggestion : suggestions_) {
                    this.suggestions.add(new Suggestion(suggestion));
                }

            } else if (((List) suggestions).get(0) instanceof Suggestion) {
                this.suggestions = (List<Suggestion>) suggestions;
            } else {

                logger.error("Invalid list. List must be of either String or Suggestion");
            }

        } else if (suggestions instanceof String[]) {
            String [] _suggestions = (String [])suggestions;
            for (String s : _suggestions) {
                this.suggestions.add(new Suggestion(s));
            }

        } else if (suggestions instanceof String){
            this.suggestions.add(new Suggestion(suggestions.toString()));

        } else {
            logger.error("Suggestion should be on of these type : String|ArrayList|List|Array");
            return null;

        }
        return this;
    }

    public RichResponse addSuggestions(String...suggestions) {

        if (suggestions == null) {
            logger.error("Invalid suggestions");
            return null;
        }

        if (suggestions instanceof String[]) {
            String [] _suggestions = (String [])suggestions;
            for (String s : _suggestions) {
                this.suggestions.add(new Suggestion(s));
            }
        } else {
            logger.error("Suggestion should be on of these type : String|ArrayList|List|Array");
            return null;
        }

        return this;
    }

    public RichResponse addSuggestionLink(String destinationName, String suggestionUrl) {
        if ((destinationName == null || destinationName.isEmpty())) {
            logger.error("destinationName cannot be empty");
            return null;
        }

        if (suggestionUrl == null || suggestionUrl.isEmpty()) {
            logger.error("suggestionUrl cannot be empty");
            return null;
        }

        this.linkOutSuggestion.setDestinationName(destinationName);
        this.linkOutSuggestion.setUrl(suggestionUrl);

        return this;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Helper to build SimpleResponse from speech and display text.
     *
     * @param {string|SimpleResponse} response String to speak, or SimpleResponse.
     *     SSML allowed.
     * @param {string} response.speech If using SimpleResponse, speech to be spoken
     *     to user.
     * @param {string=} response.displayText If using SimpleResponse, text to be shown
     *     to user.
     * @return {Object} Appropriate SimpleResponse object.
     * @private
     */
    private SimpleResponse buildSimpleResponseHelper(Object response) {
        SimpleResponse simpleResponseObj = new SimpleResponse();

        if (response instanceof String) {
            String _response = response.toString();
            return new SimpleResponse(_response, null);
        } else if (response instanceof SimpleResponse) {
            SimpleResponse response_ = (SimpleResponse) response;
            if (Util.isNotNull(response_.getTextToSpeech())) {
                return new SimpleResponse(response_.getTextToSpeech(), response_.getDisplayText());
            }

            return new SimpleResponse(response_.getSsml(), response_.getDisplayText());
        } else {
            logger.error("SimpleResponse requires a speech parameter.");
            return null;
        }
    }

    private boolean isSsml(String response) {
        if (response != null) {
            return response.matches("(?s).*(<(\\w+)[^>]*>.*</\\2>|<(\\w+)[^>]*/>).*");
        }

        return false;
    }
}