
package ca.sukhsingh.actions.on.google.response.data.google.systemIntent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "accessibilityText"
})
public class Image {

    @JsonProperty("url")
    private String url;
    @JsonProperty("accessibilityText")
    private String accessibilityText;

    public Image(String url, String accessibilityText) {
        this.url = url;
        this.accessibilityText = accessibilityText;
    }
}
