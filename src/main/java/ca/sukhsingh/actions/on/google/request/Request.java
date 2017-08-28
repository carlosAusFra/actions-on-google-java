
package ca.sukhsingh.actions.on.google.request;

import ca.sukhsingh.actions.on.google.request.originalRequest.*;
import ca.sukhsingh.actions.on.google.request.result.Context;
import ca.sukhsingh.actions.on.google.request.result.Result;
import ca.sukhsingh.actions.on.google.request.status.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sukh on 2017-08-09.
 */
public class Request {

    @JsonProperty("originalRequest")
    private OriginalRequest originalRequest;
    @JsonProperty("id")
    private String id;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("result")
    private Result result;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("sessionId")
    private String sessionId;

    public OriginalRequest getOriginalRequest() {
        return originalRequest;
    }

    public String getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLang() {
        return lang;
    }

    public Result getResult() {
        return result;
    }

    public Status getStatus() {
        return status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean hasSurfaceCapability(String requestedCapability) {
        for (Capability capability : getSurfaceCapabilities()) {
            if (capability.getName().equals(requestedCapability)) {
                return true;
            }
        }
        return false;
    }

    public String getORIntent() {
        return originalRequest.getData().getInputs().get(0).getIntent();
    }

    public String getIntent() {
        return result.getMetadata().getIntentName();
    }

    public String getUserId() {
        return getUser().getUserId();
    }

    public List<Capability> getSurfaceCapabilities() {
        return originalRequest.getData().getSurface().getCapabilities();
    }

    public String getAction() {
        return result.getAction();
    }

    public Coordinates getDeviceLocation() {
        return originalRequest.getData().getDevice().getLocation().getCoordinates();
    }

    public double getLatitude() {
        return getDeviceLocation().getLatitude();
    }

    public double getLongitude() {
        return getDeviceLocation().getLongitude();
    }

    public String getLatLong() {
        return String.valueOf(getLatitude()) + "," + String.valueOf(getLongitude());
    }

    public User getUser() {
        return originalRequest.getData().getUser();
    }

    public Profile getUserName() {
        return getUser().getProfile();
    }

    public List<Context> getContexts() {
        return result.getContexts();
    }

}


