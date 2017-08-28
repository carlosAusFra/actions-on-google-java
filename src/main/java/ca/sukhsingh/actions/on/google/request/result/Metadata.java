
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Metadata {

    @JsonProperty("intentId")
    private String intentId;
    @JsonProperty("webhookUsed")
    private String webhookUsed;
    @JsonProperty("webhookForSlotFillingUsed")
    private String webhookForSlotFillingUsed;
    @JsonProperty("nluResponseTime")
    private Integer nluResponseTime;
    @JsonProperty("intentName")
    private String intentName;

    public String getIntentId() {
        return intentId;
    }

    public String getWebhookUsed() {
        return webhookUsed;
    }

    public String getWebhookForSlotFillingUsed() {
        return webhookForSlotFillingUsed;
    }

    public Integer getNluResponseTime() {
        return nluResponseTime;
    }

    public String getIntentName() {
        return intentName;
    }
}
