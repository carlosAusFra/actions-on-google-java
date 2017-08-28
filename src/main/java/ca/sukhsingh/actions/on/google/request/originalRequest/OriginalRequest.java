
package ca.sukhsingh.actions.on.google.request.originalRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class OriginalRequest {

    @JsonProperty("source")
    private String source;
    @JsonProperty("version")
    private String version;
    @JsonProperty("data")
    private Data data;

    public String getSource() {
        return source;
    }

    public String getVersion() {
        return version;
    }

    public Data getData() {
        return data;
    }
}
