package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntentArgument {

    @JsonProperty("name")
    public String name;
    @JsonProperty("textValue")
    public String textValue;

    public void setName(String name) {
        this.name = name;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }
}
