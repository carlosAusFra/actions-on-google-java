
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Data {

    @JsonProperty("isInSandbox")
    private Boolean isInSandbox;
    @JsonProperty("surface")
    private Surface surface;
    @JsonProperty("availableSurfaces")
    private  List<AvailableSurfaces> availableSurfaces = null;
    @JsonProperty("inputs")
    private List<Input> inputs ;
    @JsonProperty("user")
    private User user;
    @JsonProperty("device")
    private Device device;
    @JsonProperty("conversation")
    private Conversation conversation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getInSandbox() {
        return isInSandbox;
    }

    public Surface getSurface() {
        return surface;
    }

    public List<AvailableSurfaces> getAvailableSurfaces() {
        return availableSurfaces;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public User getUser() {
        return user;
    }

    public Device getDevice() {
        return device;
    }

    public Conversation getConversation() {
        return conversation;
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
