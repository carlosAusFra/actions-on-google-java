
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Coordinates {

    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
