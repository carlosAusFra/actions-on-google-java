package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DialogSpec {

    @JsonProperty("requestConfirmationText")
    private String requestConfirmationText;

    @JsonProperty("requestDatetimeText")
    private String requestDatetimeText;

    @JsonProperty("requestDateText")
    private String requestDateText;

    @JsonProperty("requestTimeText")
    private String requestTimeText;

    public String getRequestConfirmationText() {
        return requestConfirmationText;
    }

    public void setRequestConfirmationText(String requestConfirmationText) {
        this.requestConfirmationText = requestConfirmationText;
    }

    public String getRequestDatetimeText() {
        return requestDatetimeText;
    }

    public void setRequestDatetimeText(String requestDatetimeText) {
        this.requestDatetimeText = requestDatetimeText;
    }

    public String getRequestDateText() {
        return requestDateText;
    }

    public void setRequestDateText(String requestDateText) {
        this.requestDateText = requestDateText;
    }

    public String getRequestTimeText() {
        return requestTimeText;
    }

    public void setRequestTimeText(String requestTimeText) {
        this.requestTimeText = requestTimeText;
    }

    @Override
    public String toString() {
        return "DialogSpec{" +
                "requestConfirmationText='" + requestConfirmationText + '\'' +
                '}';
    }
}
