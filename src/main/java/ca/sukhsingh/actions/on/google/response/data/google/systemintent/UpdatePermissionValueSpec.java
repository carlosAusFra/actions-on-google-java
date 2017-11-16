package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePermissionValueSpec {

    @JsonProperty("intent")
    public String intent;
    @JsonProperty("arguments")
    public List<IntentArgument> arguments;

    public UpdatePermissionValueSpec(String intent) {
        this.intent = intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public void setArguments(List<IntentArgument> arguments) {
        this.arguments = arguments;
    }
}

