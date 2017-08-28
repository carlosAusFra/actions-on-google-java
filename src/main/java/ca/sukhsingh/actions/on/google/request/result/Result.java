
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
    @JsonProperty("resultParameters")
    private ResultParameters resultParameters;
    @JsonProperty("contexts")
    private List<Context> contexts;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("fulfillment")
    private Fulfillment fulfillment;
    @JsonProperty("score")
    private Double score;

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

    public ResultParameters getResultParameters() {
        return resultParameters;
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
