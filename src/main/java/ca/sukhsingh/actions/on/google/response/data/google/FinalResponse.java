
package ca.sukhsingh.actions.on.google.response.data.google;

import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "richResponse"
})
public class FinalResponse {

    @JsonProperty("richResponse")
    public RichResponse richResponse;

}
