
package ca.sukhsingh.actions.on.google.request;

import ca.sukhsingh.actions.on.google.request.originalRequest.*;
import ca.sukhsingh.actions.on.google.request.result.Context;
import ca.sukhsingh.actions.on.google.request.result.Result;
import ca.sukhsingh.actions.on.google.request.status.Status;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse.RichResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.util.List;

import static ca.sukhsingh.actions.on.google.Util.*;

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

    Logger logger = Logger.getLogger(Request.class);

    public class SurfaceCapabilities {
        public static final String AUDIO_OUTPUT = "actions.capability.AUDIO_OUTPUT";
        public static final String SCREEN_OUTPUT = "actions.capability.SCREEN_OUTPUT";
    }

    /**
     * Gets the {@link User} object.
     * The user object contains information about the user, including
     * a string identifier and personal information (requires requesting permissions,
     * @return {@link User}
     */
    public User getUser() {
        if (isNull(originalRequest.getData().getUser())) {
            return null;
        }
        return originalRequest.getData().getUser();
    }

    /**
     * If granted permission to user's name in previous intent, returns user's
     * display name, family name, and given name. If name info is unavailable,
     * returns null.
     * @return
     */
    public Profile getUserName() {
        if (isNull(getUser().getProfile())) {
            return null;
        }
        return getUser().getProfile();
    }

    /**
     * Gets the user locale. Returned string represents the regional language
     * information of the user set in their Assistant settings.
     * For example, 'en-US' represents US English.
     * @return {string} User's locale, e.g. 'en-US'. Null if no locale given.
     */
    public String getUserLocale() {
        return (isNull(getUser()) && isNull(getUser().getLocale())) ? null : getUser().getLocale();
    }

    /**
     * If granted permission to device's location in previous intent, returns device's
     * location (see {@link ca.sukhsingh.actions.on.google.AssistantApp#askForPermissions|askForPermissions}).
     * If device info is unavailable, returns null.
     * @return {@link Coordinates}
     */
    public Coordinates getDeviceLocation() {
        return originalRequest.getData().getDevice().getLocation().getCoordinates();
    }

    public void getInputType() {

    }

    /**
     * Get the argument value by name from the current intent.
     * If the argument is included in originalRequest, and is not a text argument,
     * the entire argument object is returned.
     *
     * @param agrName
     * @return null|string|argument
     */
    public Object getArgument(String agrName) {
        if (isNullOrEmpty(agrName)) {
            logger.error("Invalid argument name");
            return null;
        }

        Argument argument = findArgument_(agrName);
        if (isNull(argument)) {
            logger.error("Failed to get argument value: " + agrName);
            return null;
        } else if (isNullOrEmpty(argument.getTextValue())) {
            return argument.getTextValue();
        } else {
            return argument;
        }
        //TODO if (!this.isNotApiVersionOne_()) { 1235
    }

    public void getTransactionRequirementsResult() {

    }

    public void getDeliveryAddress() {

    }

    public void getTransactionDecision() {

    }

    public void getUserConfirmation() {

    }

    public void getDateTime() {

    }

    public void getSignInStatus() {

    }

    /**
     * Returns true if user device has a given surface capability.
     *
     * @param {string} capability Must be one of {@link SurfaceCapabilities}.
     * @return
     */
    public boolean hasSurfaceCapability(String requestedCapability) {
        for (Capability capability : getSurfaceCapabilities()) {
            if (capability.getName().equals(requestedCapability)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets surface capabilities of user device.
     *
     * @return {Array<string>} Supported surface capabilities, as defined in
     *     AssistantApp.SurfaceCapabilities.
     */
    public List<Capability> getSurfaceCapabilities() {
        return originalRequest.getData().getSurface().getCapabilities();
    }

    /**
     * Returns true if the app is being tested in sandbox mode. Enable sandbox
     * mode in the (Actions console)[console.actions.google.com] to test
     * transactions.
     *
     * @return {boolean} True if app is being used in Sandbox mode.
     */
    public boolean isInSandbox() {
        return getOriginalRequest().getData().getInSandbox();
    }

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

    public String getORIntent() {
        return originalRequest.getData().getInputs().get(0).getIntent();
    }

    public String getIntent() {
        return result.getMetadata().getIntentName();
    }

    public String getUserId() {
        return getUser().getUserId();
    }

    public String getAction() {
        return result.getAction();
    }

    public double getLatitude() {
        return getDeviceLocation().getLatitude();
    }

    public double getLongitude() {
        return getDeviceLocation().getLongitude();
    }

    public List<Context> getContexts() {
        return result.getContexts();
    }

    public boolean isRequestFromApiAi(String key, String value) {
        return false;
    }

    public Object getContextArgument(String contextName, String argName) {
        return null;
    }

    public RichResponse getIncomingRichResponse() { //line:247
        return null;
    }

    public Object getIncomingList() {
        return null;
    }

    public Object getIncomingCarousel() {
        return null;
    }

    public String getSelectedOption() {
        return null;
    }
    
    private Argument findArgument_(String...targets) {
       // Argument argument = new Argument();
        if (isNotNull(getOriginalRequest().getData()) && isNotNull(getOriginalRequest().getData().getInputs())) {
            for (Input input : getOriginalRequest().getData().getInputs()) {
                if (isNotNull(input.getArguments())) {
                    for (Argument argument : input.getArguments()) {
                        for (String target : targets) {
                            if (argument.getName().equalsIgnoreCase(target)) {
                                return argument;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}


