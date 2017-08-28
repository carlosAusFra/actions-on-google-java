
package ca.sukhsingh.actions.on.google.response.data.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "richInitialPrompt",
    "initialPrompts",
    "noInputPrompts"
})
public class InputPrompt {

    @JsonProperty("richInitialPrompt")
    public RichResponse richInitialPrompt;

    @JsonProperty("initialPrompts")
    public RichResponse initialPrompts;

    @JsonProperty("noInputPrompts")
    public List<SimpleResponse> noInputPrompts = null;

    public void setRichInitialPrompt(RichResponse richInitialPrompt) {
        this.richInitialPrompt = richInitialPrompt;
    }

    public void setInitialPrompts(RichResponse initialPrompts) {
        this.initialPrompts = initialPrompts;
    }

    public void setNoInputPrompts(List<SimpleResponse> noInputPrompts) {
        this.noInputPrompts = noInputPrompts;
    }
}
