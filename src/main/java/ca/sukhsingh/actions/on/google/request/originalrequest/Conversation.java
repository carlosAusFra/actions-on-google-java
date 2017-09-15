
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Conversation {

    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("conversationToken")
    private String conversationToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getConversationId() {
        return conversationId;
    }

    public String getType() {
        return type;
    }

    public String getConversationToken() {
        return conversationToken;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
