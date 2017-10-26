package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogSpec {
    @JsonProperty("requestConfirmationText")
    private String requestConfirmationText;

    public String getRequestConfirmationText() {
        return requestConfirmationText;
    }

    public void setRequestConfirmationText(String requestConfirmationText) {
        this.requestConfirmationText = requestConfirmationText;
    }
}
