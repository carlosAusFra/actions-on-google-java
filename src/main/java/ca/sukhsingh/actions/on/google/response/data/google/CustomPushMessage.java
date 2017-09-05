
package ca.sukhsingh.actions.on.google.response.data.google;

import ca.sukhsingh.actions.on.google.response.data.google.RichResponse.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderUpdate"
})
public class CustomPushMessage {

    @JsonProperty("orderUpdate")
    public Item.OrderUpdate orderUpdate;

}
