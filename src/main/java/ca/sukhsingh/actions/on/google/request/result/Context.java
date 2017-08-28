
package ca.sukhsingh.actions.on.google.request.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Context {

    @JsonProperty("name")
    private String name;
    @JsonProperty("parameters")
    private ContextParameters contextParameters;
    @JsonProperty("lifespan")
    private Integer lifespan;

}
