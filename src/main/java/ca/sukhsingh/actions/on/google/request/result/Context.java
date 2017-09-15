
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Context {

    @JsonProperty("name")
    private String name;
    @JsonProperty("parameters")
    private Parameters contextParameters;
    @JsonProperty("lifespan")
    private Integer lifespan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getName() {
        return name;
    }

    public Parameters getContextParameters() {
        return contextParameters;
    }

    public Integer getLifespan() {
        return lifespan;
    }
}
