
package ca.sukhsingh.actions.on.google.response.data.google.systemIntent;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "synonyms"
})
public class OptionInfo {

    @JsonProperty("key")
    private String key;
    @JsonProperty("synonyms")
    private List<String> synonyms = null;

    public void setKey(String key) {
        this.key = key;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getKey() {
        return key;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }
}
