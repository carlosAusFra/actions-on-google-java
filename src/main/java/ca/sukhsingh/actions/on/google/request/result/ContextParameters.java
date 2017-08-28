
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class ContextParameters {

    @JsonProperty("PERMISSION")
    private String permission;

    public String getPermission() {
        return permission;
    }

}
