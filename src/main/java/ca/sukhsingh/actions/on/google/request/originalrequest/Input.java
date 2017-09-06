
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public List<RawInput> getRawInputs() {
        return rawInputs;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getIntent() {
        return intent;
    }
}
