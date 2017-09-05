
package ca.sukhsingh.actions.on.google.response.data.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    @JsonProperty("google")
    private Google google;

    public Google getGoogle() {
        return google;
    }

    public void setGoogle(Google google) {
        this.google = google;
    }
}
