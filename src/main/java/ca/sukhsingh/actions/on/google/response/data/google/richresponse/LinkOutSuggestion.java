package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "destinationName",
        "url"
})
public class LinkOutSuggestion {

    @JsonProperty("destinationName")
    public String destinationName;
    @JsonProperty("url")
    public String url;

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

