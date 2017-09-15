
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class RawInput {

    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("query")
    private String query;
    @JsonProperty("inputType")
    private String inputType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCreateTime() {
        return createTime;
    }

    public String getQuery() {
        return query;
    }

    public String getInputType() {
        return inputType;
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
