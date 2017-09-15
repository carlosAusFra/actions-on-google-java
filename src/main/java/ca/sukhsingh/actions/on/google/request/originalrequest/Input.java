
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Input {

    @JsonProperty("rawInputs")
    private List<RawInput> rawInputs ;
    @JsonProperty("arguments")
    private List<Argument> arguments ;
    @JsonProperty("intent")
    private String intent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<RawInput> getRawInputs() {
        return rawInputs;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getIntent() {
        return intent;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
