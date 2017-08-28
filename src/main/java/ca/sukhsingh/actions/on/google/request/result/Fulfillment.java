
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Fulfillment {

    @JsonProperty("speech")
    private String speech;
    @JsonProperty("messages")
    private List<Message> messages ;

    public String getSpeech() {
        return speech;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
