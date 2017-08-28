
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Message {

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("speech")
    private String speech;

    public Integer getType() {
        return type;
    }

    public String getSpeech() {
        return speech;
    }
}
