
package ca.sukhsingh.actions.on.google.request.originalRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Surface {

    @JsonProperty("capabilities")
    private List<Capability> capabilities;

    public List<Capability> getCapabilities() {
        return capabilities;
    }

}
