
package ca.sukhsingh.actions.on.google.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemIntent {

    @JsonProperty("intent")
    private String intent;
    @JsonProperty("data")
    private SystemIntentData data;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public SystemIntentData getData() {
        return data;
    }

    public void setData(SystemIntentData data) {
        this.data = data;
    }
}
