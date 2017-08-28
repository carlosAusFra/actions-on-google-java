
package ca.sukhsingh.actions.on.google.request.originalRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Argument {

    @JsonProperty("name")
    private String name;
    @JsonProperty("rawText")
    private String rawText;
    @JsonProperty("boolValue")
    private Boolean boolValue;
    @JsonProperty("textValue")
    private String textValue;
    @JsonProperty("datetimeValue")
    private DatetimeValue datetimeValue;
    @JsonProperty("extension")
    private Extension extension;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public String getRawText() {
        return rawText;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public DatetimeValue getDatetimeValue() {
        return datetimeValue;
    }

    public Extension getExtension() {
        return extension;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
