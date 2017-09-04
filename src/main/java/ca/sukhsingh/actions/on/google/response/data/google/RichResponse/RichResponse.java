package ca.sukhsingh.actions.on.google.response.data.google.RichResponse;

/**
 * Created by sukhsingh on 2017-08-26.
 */

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

    public LinkOutSuggestion getLinkOutSuggestion() {
        return linkOutSuggestion;
    }

    public RichResponse() {
        this.items = new ArrayList<>();
        this.suggestions = new ArrayList<>();
        this.linkOutSuggestion = new LinkOutSuggestion();
    }

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
                logger.error("Cannot include >2 SimpleResponses in RichResponse");
                return this;
            }
        }
        SimpleResponse _simpleResponse = buildSimpleResponseHelper_(simpleResponse);
        //TODO Check first if needs to replace BasicCard at beginning of items list
        this.items.add(new Item(_simpleResponse));
        return this;
    }

    public RichResponse addBasicCard(BasicCard basicCard) {
        
        if(basicCard == null) {
            logger.error("Invalid basicCard");
            return null;
        }

        for (Item item : this.items) {
            if (item.getBasicCard() != null) {
                logger.error("Cannot include >1 BasicCard in RichResponse");
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
            List<Suggestion> suggestions_ = (ArrayList) suggestions;
            for (Suggestion suggestion : suggestions_) {
                this.suggestions.add(new Suggestion(suggestion.getTitle()));
            }
        }

        if (suggestions instanceof List) {
            List<String> suggestions_ = (List<String>) suggestions;
            for (String suggestion : suggestions_) {
                this.suggestions.add(new Suggestion(suggestion));
            }
        }

        if (suggestions instanceof String[]) {
          String [] _suggestions = (String [])suggestions;
          for (String s : _suggestions) {
              this.suggestions.add(new Suggestion(s));
          }
        } else {
            this.suggestions.add(new Suggestion(suggestions.toString()));
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
    private SimpleResponse buildSimpleResponseHelper_(Object response) {
        SimpleResponse simpleResponseObj = new SimpleResponse();

        if (response instanceof String) {
            String _response = response.toString();
            return isSsml(_response)
                    ? new SimpleResponse(null, _response, null)
                    : new SimpleResponse(_response, null, null);
        } else if (response instanceof SimpleResponse) {
            SimpleResponse response_ = (SimpleResponse) response;
            return isSsml(response_.getSsml())
                    ? new SimpleResponse(null, response_.getSsml(), response_.getDisplayText())
                    : new SimpleResponse(response_.getTextToSpeech(), null, response_.getDisplayText());
        } else {
            //TODO Logging with error
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