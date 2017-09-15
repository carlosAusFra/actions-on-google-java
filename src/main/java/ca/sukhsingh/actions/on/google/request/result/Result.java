
package ca.sukhsingh.actions.on.google.request.result;

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
public class Result {

    @JsonProperty("source")
    private String source;
    @JsonProperty("resolvedQuery")
    private String resolvedQuery;
    @JsonProperty("speech")
    private String speech;
    @JsonProperty("action")
    private String action;
    @JsonProperty("actionIncomplete")
    private Boolean actionIncomplete;
    @JsonProperty("parameters")
    private Parameters parameters;
    @JsonProperty("contexts")
    private List<Context> contexts;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("fulfillment")
    private Fulfillment fulfillment;
    @JsonProperty("score")
    private Double score;
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

    public String getSource() {
        return source;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public String getSpeech() {
        return speech;
    }

    public String getAction() {
        return action;
    }

    public Boolean getActionIncomplete() {
        return actionIncomplete;
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Fulfillment getFulfillment() {
        return fulfillment;
    }

    public Double getScore() {
        return score;
    }

}
