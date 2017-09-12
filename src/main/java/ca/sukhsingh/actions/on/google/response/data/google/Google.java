
package ca.sukhsingh.actions.on.google.response.data.google;

import ca.sukhsingh.actions.on.google.response.data.google.systemintent.SystemIntent;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.SimpleResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sukhSingh on 2017-08-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Google {
    @JsonProperty("conversationToken")
    public String conversationToken;
    @JsonProperty("expectUserResponse")
    public Boolean expectUserResponse;
    @JsonProperty("expectedInputs")
    public List<ExpectedInput> expectedInputs = null;
    @JsonProperty("finalResponse")
    public FinalResponse finalResponse;
    @JsonProperty("customPushMessage")
    public CustomPushMessage customPushMessage;
    @JsonProperty("responseMetadata")
    public ResponseMetadata responseMetadata;
    @JsonProperty("isInSandbox")
    public Boolean isInSandbox;
    @JsonProperty("isSsml")
    public Boolean isSsml;
    @JsonProperty("noInputPrompts")
    public List<SimpleResponse> noInputPrompts = null;
    @JsonProperty("richResponse")
    public RichResponse richResponse;
    @JsonProperty("systemIntent")
    private SystemIntent systemIntent;

    public Google() {
    }

    public Google(SystemIntent systemIntent) {
        this.systemIntent = systemIntent;
    }

    public void setConversationToken(String conversationToken) {
        this.conversationToken = conversationToken;
    }

    public void setExpectUserResponse(Boolean expectUserResponse) {
        this.expectUserResponse = expectUserResponse;
    }

    public void setExpectedInputs(List<ExpectedInput> expectedInputs) {
        this.expectedInputs = expectedInputs;
    }

    public void setFinalResponse(FinalResponse finalResponse) {
        this.finalResponse = finalResponse;
    }

    public void setCustomPushMessage(CustomPushMessage customPushMessage) {
        this.customPushMessage = customPushMessage;
    }

    public void setResponseMetadata(ResponseMetadata responseMetadata) {
        this.responseMetadata = responseMetadata;
    }

    public void setInSandbox(Boolean inSandbox) {
        isInSandbox = inSandbox;
    }

    public void setSsml(Boolean ssml) {
        isSsml = ssml;
    }

    public void setNoInputPrompts(List<SimpleResponse> noInputPrompts) {
        this.noInputPrompts = noInputPrompts;
    }

    public void setRichResponse(RichResponse richResponse) {
        this.richResponse = richResponse;
    }

    public void setSystemIntent(SystemIntent systemIntent) {
        this.systemIntent = systemIntent;
    }

    public RichResponse getRichResponse() {
        return richResponse;
    }

    public List<SimpleResponse> getNoInputPrompts() {
        return noInputPrompts;
    }

    public SystemIntent getSystemIntent() {
        return systemIntent;
    }
}
