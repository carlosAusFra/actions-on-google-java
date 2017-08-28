
package ca.sukhsingh.actions.on.google.request.originalRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class User {

    @JsonProperty("profile")
    private Profile profile;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("permissions")
    private List<String> permissions;

    public Profile getProfile() {
        return profile;
    }

    public String getLocale() {
        return locale;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
