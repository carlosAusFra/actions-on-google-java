
package ca.sukhsingh.actions.on.google.response.data.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "inputPrompt",
    "possibleIntents",
    "speechBiasingHints"
})
public class ExpectedInput {

    @JsonProperty("inputPrompt")
    public InputPrompt inputPrompt;
    @JsonProperty("possibleIntents")
    public List<Object> possibleIntents = null;
    @JsonProperty("speechBiasingHints")
    public List<String> speechBiasingHints = null;

    public void setInputPrompt(InputPrompt inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    public void setPossibleIntents(List<Object> possibleIntents) {
        this.possibleIntents = possibleIntents;
    }

    public void setSpeechBiasingHints(List<String> speechBiasingHints) {
        this.speechBiasingHints = speechBiasingHints;
    }
}
