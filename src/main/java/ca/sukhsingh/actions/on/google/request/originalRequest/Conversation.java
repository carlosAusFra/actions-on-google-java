
package ca.sukhsingh.actions.on.google.request.originalRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public String getConversationId() {
        return conversationId;
    }

    public String getType() {
        return type;
    }

    public String getConversationToken() {
        return conversationToken;
    }
}
