
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Data {

    @JsonProperty("isInSandbox")
    private Boolean isInSandbox;
    @JsonProperty("surface")
    private Surface surface;
    @JsonProperty("inputs")
    private List<Input> inputs ;
    @JsonProperty("user")
    private User user;
    @JsonProperty("device")
    private Device device;
    @JsonProperty("conversation")
    private Conversation conversation;

    public Boolean getInSandbox() {
        return isInSandbox;
    }

    public Surface getSurface() {
        return surface;
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

    public void setInSandbox(Boolean inSandbox) {
        isInSandbox = inSandbox;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
