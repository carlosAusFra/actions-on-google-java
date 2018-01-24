
package ca.sukhsingh.actions.on.google.request;

import ca.sukhsingh.actions.on.google.AssistantApp;
import ca.sukhsingh.actions.on.google.DialogflowApp;
import ca.sukhsingh.actions.on.google.request.originalrequest.*;
import ca.sukhsingh.actions.on.google.request.result.Context;
import ca.sukhsingh.actions.on.google.request.result.Result;
import ca.sukhsingh.actions.on.google.request.status.Status;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.sukhsingh.actions.on.google.Util.*;

/**
 * Created by sukh on 2017-08-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    Logger logger = Logger.getLogger(Request.class);

    public class SurfaceCapabilities {
        public static final String AUDIO_OUTPUT = "actions.capability.AUDIO_OUTPUT";
        public static final String SCREEN_OUTPUT = "actions.capability.SCREEN_OUTPUT";
    }

    /**
     * Get the argument value by name from the current intent.
     * If the argument is included in originalrequest, and is not a text argument,
     * the entire argument object is returned.
     *
     * @param agrName {@link String}
     * @return Object null|string|argument
     */
    @JsonIgnore
    public Object getArgumentCommon(String agrName) {
        return getArgument(agrName);
    }

    /**
     * Equivalent getArgumentCommon
     *
     * @param agrName {@link String}
     * @return Object null|string|argument
     */
    @JsonIgnore
    public Object getArgument(String agrName) {
        if (isNullOrEmpty(agrName)) {
            logger.error("Invalid argument name");
            return null;
        }

        Argument argument = findArgument(agrName);
        if (isNull(argument)) {
            logger.error("Failed to get argument value: " + agrName);
            return null;
        } else if (!isNullOrEmpty(argument.getTextValue())) {
            return argument.getTextValue();
        } else {
            return argument;
        }
    }

    /**
     * Returns the set of other available surfaces for the user.
     *
     * @return {@link List} Empty if no available surfaces.
     */
    @JsonIgnore
    public List<AvailableSurfaces> getAvxailableSurfaces() {
        List<AvailableSurfaces> availableSurfaces = originalRequest.getData().getAvailableSurfaces();
        return isNotNull(availableSurfaces) ? availableSurfaces : new ArrayList<>();
    }

    /**
     * Gets user provided date and time. Use after askForDateTime.
     *
     * @return {@link DatetimeValue} Date and time given by the user. Null if no user
     *     date and time given.
     */
    @JsonIgnore
    public DatetimeValue getDateTime() {
        Argument argument = findArgument(DialogflowApp.BuiltInArgNames.DATETIME);
        if (isNotNull(argument)) {
            return argument.getDatetimeValue();
        }
        debug("Failed to get date/time information");
        return null;
    }

//    /**
//     * Gets order delivery address. Only use after calling askForDeliveryAddress.
//     */
//    public void getDeliveryAddress() {
//
//    }

    /**
     * If granted permission to device's location in previous intent, returns device's location.
     * If device info is unavailable, returns null.
     * @return {@link Coordinates}
     */
    @JsonIgnore
    public Coordinates getDeviceLocation() {
        return originalRequest.getData().getDevice().getLocation().getCoordinates();
    }

    /**
     * Gets type of input used for this request.
     *
     * @return {@link String} Null if no input type given.
     */
    @JsonIgnore
    public String getInputType() {
        logger.debug("getInputType");
        if (isNotNull(getOriginalRequest().getData().getInputs())) {
            for (Input input : getOriginalRequest().getData().getInputs()) {
                if (isNotNull(input.getRawInputs())) {
                    for (RawInput rawInput : input.getRawInputs()) {
                        if (isNotNull(rawInput.getInputType())) {
                            return rawInput.getInputType();
                        }
                    }
                }
            }
        }
        logger.error("No input type in incoming request");
        return null;
    }

    @JsonIgnore
    public java.util.Date getLastSeen() {
        User user = getUser();
        if (isNull(user)) {
            return null;
        }
        String lastSeen = user.getLastSeen();
        if (isNull(lastSeen)) {
            return null;
        }
        return new Date(lastSeen);
    }
    @JsonIgnore
    public void getRepromptCount() {}
    @JsonIgnore
    public void getSignInStatus() {}

    /**
     * Gets surface capabilities of user device.
     *
     * @return List Supported surface capabilities, as defined in SurfaceCapabilities.
     */
    @JsonIgnore
    public List<Capability> getSurfaceCapabilities() {
        logger.debug("getSurfaceCapabilities");
        if (isNotNull(originalRequest.getData().getSurface().getCapabilities())) {
            return originalRequest.getData().getSurface().getCapabilities();
        }
        logger.error("No capabilities found");
        return null;
    }

    public void getTransactionDecision() {}

    public void getTransactionRequirementsResult() {}

    /**
     * Gets the {@link User} object.
     * The user object contains information about the user, including
     * a string identifier and personal information (requires requesting permissions,
     * @return {@link User}
     */
    @JsonIgnore
    public User getUser() {
        if (isNull(originalRequest.getData().getUser())) {
            logger.error("No user object");
            return null;
        }
        return originalRequest.getData().getUser();
    }

    /**
     * Gets confirmation decision. Use after askForConfirmation.
     *
     * @return {@link Object} False if user replied with negative response. Null if no user
     *     confirmation decision given.
     */
    @JsonIgnore
    public Object getUserConfirmation() {
        debug("getUserConfirmation");
        Argument argument = findArgument(DialogflowApp.BuiltInArgNames.CONFIRMATION);
        if (isNotNull(argument)) {
            return argument.getBoolValue();
        }
        debug("Failed to get confirmation decision information");
        return null;
    }

    /**
     * Gets the user locale. Returned string represents the regional language
     * information of the user set in their Assistant settings.
     * For example, 'en-US' represents US English.
     * @return {string} User's locale, e.g. 'en-US'. Null if no locale given.
     */
    @JsonIgnore
    public String getUserLocale() {
        return (isNull(getUser()) && isNull(getUser().getLocale())) ? null : getUser().getLocale();
    }

    /**
     * If granted permission to user's name in previous intent, returns user's
     * display name, family name, and given name. If name info is unavailable,
     * returns null.
     * @return Profile
     */
    @JsonIgnore
    public String getUserName() {
        if (isNull(getUser().getProfile())) {
            return null;
        }
        return getUser().getProfile().getDisplayName();
    }

    /**
     * Returns true if user has an available surface which includes all given
     * capabilities. Available surfaces capabilities may exist on surfaces other
     * than that used for an ongoing conversation.
     *
     *
     * @param capabilities capabilities Must be one of {@link SurfaceCapabilities}
     * @return {@link Boolean} True if user has a capability available on some surface.
     */
    @JsonIgnore
    public boolean hasAvailableSurfaceCapabilities(String capabilities) {
        debug("hasAvailableSurfaceCapabilities : " + capabilities);
        AvailableSurfaces availableSurfaces = originalRequest.getData().getAvailableSurfaces().get(0);
        for (Capability capability :availableSurfaces.capabilities) {
            if (capability.getName().equalsIgnoreCase(capabilities)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if user device has a given surface capability.
     *
     * @param requestedCapability {@link String} capability Must be one of SurfaceCapabilities.
     * @return boolean
     */
    @JsonIgnore
    public boolean hasSurfaceCapability(String requestedCapability) {
        if (isNotNull(getSurfaceCapabilities())) {
            for (Capability capability : getSurfaceCapabilities()) {
                if (capability.getName().equals(requestedCapability)) {
                    return true;
                }
            }
        }
        return false;
    }

    @JsonIgnore
    public void isFinalReprompt() {}

    /**
     * Returns true if the app is being tested in sandbox mode. Enable sandbox
     * mode in the (Actions console)[console.actions.google.com] to test
     * transactions.
     *
     * @return {boolean} True if app is being used in Sandbox mode.
     */
    @JsonIgnore
    public boolean isInSandbox() {
        return getOriginalRequest().getData().getInSandbox();
    }

    /**
     * Returns the result of the AskForNewSurface helper.
     *
     * @return {@link Boolean} True if user has triggered conversation on a new device
     *     following the NEW_SURFACE intent.
     */
    @JsonIgnore
    public boolean isNewSurface() {
        Argument argument = findArgument(AssistantApp.BuiltInArgNames.NEW_SURFACE);
        return isNotNull(argument) &&
                isNotNull(argument.getExtension()) &&
                isNotNull(argument.getExtension().getStatus()) &&
                argument.getExtension().getStatus().equalsIgnoreCase("ok");
    }

    @JsonIgnore
    public boolean isPermissionGranted() {
        Object grantObj = this.getContextParameter("actions_intent_permission", "PERMISSION");
        if (grantObj instanceof String) {
            String grant = (String) grantObj;
            if (grant.equalsIgnoreCase("false")) {
                return false;
            }
            return true;
        }
        return (boolean) grantObj;

    }

    @JsonIgnore
    public void isUpdateRegistered() {}


    // ##### EXTRA METHODS THEN GOOGLE NODE.JS LIBRARY ##### //

    /**
     * Getting all the additional parameter for results
     * @return {@link Map} map of property with name as key
     */
    @JsonIgnore
    public Map<String, Object> getResultParameters() {
        if (isNotNull(getResult().getParameters().getAdditionalProperties())) {
            return getResult().getParameters().getAdditionalProperties();
        }
        logger.error("no parameters found ");
        return null;
    }

    @JsonIgnore
    public String getResultParameter(String propertyName) {
        Map<String, Object> additionalProperties = getResultParameters();
        if (isNotNull(additionalProperties)) {
            if (additionalProperties.containsKey(propertyName)) {
                return (String)additionalProperties.get(propertyName);
            }
        }
        logger.error("no parameters with property name found ");
        return null;
    }

    @JsonIgnore
    public Map<String, Object> getContextParameters(String contextName) {
        if (isNotNull(getContext(contextName))) {
            Context context = getContext(contextName);
            if (isNotNull(context.getAdditionalProperties())) {
                return context.getContextParameters().getAdditionalProperties();
            }
        }
        logger.error("No contextName found");
        return null;
    }

    @JsonIgnore
    public Object getContextParameter(String contextName, String propertyName) {
        Map<String, Object> additionalProperties = getContextParameters(contextName);
        if (isNotNull(additionalProperties)) {
            if (additionalProperties.containsKey(propertyName)) {
                return additionalProperties.get(propertyName);
            }
        }
        logger.error("No contextName with property name found");
        return null;
    }

    @JsonIgnore
    public boolean hasScreenCapability() {
        return hasSurfaceCapability(Request.SurfaceCapabilities.SCREEN_OUTPUT);
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

    public String getIntent() {
        return result.getMetadata().getIntentName();
    }

    @JsonIgnore
    public String getUserId() {
        return getUser().getUserId();
    }

    public String getAction() {
        return result.getAction();
    }

    @JsonIgnore
    public double getLatitude() {
        return getDeviceLocation().getLatitude();
    }

    @JsonIgnore
    public double getLongitude() {
        return getDeviceLocation().getLongitude();
    }

    /**
     * Returns the incoming context by name for this intent.
     *
     * @param name name of the context
     * @return {@link Context} if found then return context otherwise null
     */
    @JsonIgnore
    public Context getContext(String name) {
        if (isNull(result.getContexts())) {
            logger.error("No contexts included in request");
            return null;
        }

        for (Context context : result.getContexts()) {
            if (context.getName().equals(name)){
                return context;
            }
        }
        logger.debug("Failed to get context with : " + name);
        return null;
    }

    /**
     * Returns the incoming contexts for this intent.
     *
     * @return {@link Context} if found then return context otherwise null
     */
    @JsonIgnore
    public List<Context> getContexts() {
        if (isNull(result.getContexts())) {
            logger.error("No contexts included in request");
            return null;
        }
        return result.getContexts();
    }

    @JsonIgnore
    public String getConversationType() {
        return this.getOriginalRequest().getData().getConversation().getType();
    }
    
    private Argument findArgument(String...targets) {
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

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private void debug(String message) {
        logger.debug(message);
    }
}


