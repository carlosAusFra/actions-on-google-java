
package ca.sukhsingh.actions.on.google.request;

import ca.sukhsingh.actions.on.google.AssistantApp;
import ca.sukhsingh.actions.on.google.DialogflowApp;
import ca.sukhsingh.actions.on.google.request.originalrequest.*;
import ca.sukhsingh.actions.on.google.request.result.Context;
import ca.sukhsingh.actions.on.google.request.result.Result;
import ca.sukhsingh.actions.on.google.request.status.Status;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
            logger.error("No user object");
            return null;
        }
        return originalRequest.getData().getUser();
    }

    /**
     * If granted permission to user's name in previous intent, returns user's
     * display name, family name, and given name. If name info is unavailable,
     * returns null.
     * @return Profile
     */
    public String getUserName() {
        if (isNull(getUser().getProfile())) {
            return null;
        }
        return getUser().getProfile().getDisplayName();
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
     * If granted permission to device's location in previous intent, returns device's location.
     * If device info is unavailable, returns null.
     * @return {@link Coordinates}
     */
    public Coordinates getDeviceLocation() {
        return originalRequest.getData().getDevice().getLocation().getCoordinates();
    }

    /**
     * Get the argument value by name from the current intent.
     * If the argument is included in originalrequest, and is not a text argument,
     * the entire argument object is returned.
     *
     * @param agrName {@link String}
     * @return Object null|string|argument
     */
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
     * Getting all the additional parameter for results
     * @return {@link Map} map of property with name as key
     */
    public Map<String, Object> getResultParameters() {
        if (isNotNull(getResult().getParameters().getAdditionalProperties())) {
            return getResult().getParameters().getAdditionalProperties();
        }
        logger.error("no parameters found ");
        return null;
    }

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


//    public void getTransactionRequirementsResult() {
//
//    }
//
//    public void getDeliveryAddress() {
//
//    }
//
//    public void getTransactionDecision() {
//
//    }

    /**
     * Gets confirmation decision. Use after askForConfirmation.
     *
     * @return {@link Object} False if user replied with negative response. Null if no user
     *     confirmation decision given.
     */
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
     * Gets user provided date and time. Use after askForDateTime.
     *
     * @return {@link DatetimeValue} Date and time given by the user. Null if no user
     *     date and time given.
     */
    public DatetimeValue getDateTime() {
        Argument argument = findArgument(DialogflowApp.BuiltInArgNames.DATETIME);
        if (isNotNull(argument)) {
            return argument.getDatetimeValue();
        }
        debug("Failed to get date/time information");
        return null;
    }
//
//    public void getSignInStatus() {
//
//    }

    public boolean isPermissionGranted() {
        String grant = (String) this.getContextParameter("actions_intent_permission", "PERMISSION");
        if (grant.equalsIgnoreCase("false")) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if user device has a given surface capability.
     *
     * @param requestedCapability {@link String} capability Must be one of SurfaceCapabilities.
     * @return boolean
     */
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

    public boolean hasScreenCapability() {
        return hasSurfaceCapability(Request.SurfaceCapabilities.SCREEN_OUTPUT);
    }

    /**
     * Gets surface capabilities of user device.
     *
     * @return List Supported surface capabilities, as defined in SurfaceCapabilities.
     */
    public List<Capability> getSurfaceCapabilities() {
        logger.debug("getSurfaceCapabilities");
        if (isNotNull(originalRequest.getData().getSurface().getCapabilities())) {
            return originalRequest.getData().getSurface().getCapabilities();
        }
        logger.error("No capabilities found");
        return null;
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

    /**
     * Returns the incoming context by name for this intent.
     *
     * @param name name of the context
     * @return {@link Context} if found then return context otherwise null
     */
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
    public List<Context> getContexts() {
        if (isNull(result.getContexts())) {
            logger.error("No contexts included in request");
            return null;
        }
        return result.getContexts();
    }

//    getContextArgument

//    public Carousel getIncomingCarousel() {
//        logger.debug("getIncomingCarousel");
//        if (isNotNull(result.getFulfillment().getMessages())) {
//            for (Message message : result.getFulfillment().getMessages()) {
//
//            }
//        }
//    }

//    getIncomingList

//    getIncomingRichResponse

    /**
     * Gets type of input used for this request.
     *
     * @return {@link String} Null if no input type given.
     */
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private void debug(String message) {
        logger.debug(message);
    }
}


