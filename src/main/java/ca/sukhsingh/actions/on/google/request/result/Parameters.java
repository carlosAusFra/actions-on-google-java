
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parameters {

    @JsonProperty("followup_input")
    public String followupResponse;

    public String getUserResponse() {
        return followupResponse;
    }

    public void setUserResponse(String userResponse) {
        this.followupResponse = userResponse;
    }
}
