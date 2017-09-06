
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Profile {

    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("familyName")
    private String familyName;

    public String getDisplayName() {
        return displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }
}
