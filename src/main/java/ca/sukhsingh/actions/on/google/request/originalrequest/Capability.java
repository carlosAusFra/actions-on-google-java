
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Capability {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

}
