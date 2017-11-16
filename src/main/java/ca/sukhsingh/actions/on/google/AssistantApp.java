package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static ca.sukhsingh.actions.on.google.Util.*;

/**
 * <p>
 * The Actions on Google client library AssistantApp base class.
 *</p>
 * <p>
 * This class contains the methods that are shared between platforms to support the conversation API
 * protocol from Assistant. It also exports the 'State' class as a helper to represent states by
 * name.
 *</p>
 * @author Sukhvinder Singh
 * @since 2017-08-31
 */
public class AssistantApp {

    Logger logger = Logger.getLogger(AssistantApp.class);

    /**
     * List of standard intents that the app provides.
     */
    public class StandardIntents {
        /** App fires PERMISSION intent when action invokes askForPermission. */
        public static final String PERMISSION = "actions.intent.PERMISSION";
        /** App fires OPTION intent when user chooses from options provided. */
        public static final String OPTION = "actions.intent.OPTION";
        /** App fires TRANSACTION_REQUIREMENTS_CHECK intent when action sets up transaction. */
        public static final String TRANSACTION_REQUIREMENTS_CHECK = "actions.intent.TRANSACTION_REQUIREMENTS_CHECK";
        /** App fires DELIVERY_ADDRESS intent when action asks for delivery address. */
        public static final String DELIVERY_ADDRESS = "actions.intent.DELIVERY_ADDRESS";
        /** App fires TRANSACTION_DECISION intent when action asks for transaction decision. */
        public static final String TRANSACTION_DECISION = "actions.intent.TRANSACTION_DECISION";
        /** App fires CONFIRMATION intent when requesting affirmation from user. */
        public static final String CONFIRMATION = "actions.intent.CONFIRMATION";
        /** App fires DATETIME intent when requesting date/time from user. */
        public static final String DATETIME = "actions.intent.DATETIME";
        /** App fires SIGN_IN intent when requesting sign-in from user. */
        public static final String SIGN_IN = "actions.intent.SIGN_IN";
        /** App fires NO_INPUT intent when user doesn't provide input. */
        public static final String NO_INPUT = "actions.intent.NO_INPUT";
        /** App fires CANCEL intent when user exits app mid-dialog. */
        public static final String CANCEL = "actions.intent.CANCEL";
        /** App fires NEW_SURFACE intent when requesting handoff to a new surface from user. */
        public static final String NEW_SURFACE = "actions.intent.NEW_SURFACE";
        /** App fires REGISTER_UPDATE intent when requesting the user to register for proactive updates. */
        public static final String REGISTER_UPDATE = "actions.intent.REGISTER_UPDATE";
        /** App receives CONFIGURE_UPDATES intent to indicate a custom REGISTER_UPDATE intent should be sent. */
        public static final String CONFIGURE_UPDATES = "actions.intent.CONFIGURE_UPDATES";
    }

    /**
     * List of supported permissions the app supports.
     */
    public class SupportedPermissions {
        /**
         * The user's name as defined in the
         */
        public static final String NAME = "NAME";
        /**
         * The location of the user's current device, as defined in the
         */
        public static final String DEVICE_PRECISE_LOCATION = "DEVICE_PRECISE_LOCATION";
        /**
         * City and zipcode corresponding to the location of the user's current device, as defined in the
         */
        public static final String DEVICE_COARSE_LOCATION = "DEVICE_COARSE_LOCATION";
        /**
         * Confirmation to receive proactive content at any time from the app.
         */
        public static final String UPDATE = "UPDATE";

    }

    /**
     * List of built-in argument names.
     */
    public class BuiltInArgNames {
        /** Permission granted argument. */
        public static final String PERMISSION_GRANTED = "PERMISSION";
        /** Option selected argument. */
        public static final String OPTION = "OPTION";
        /** Transaction requirements check result argument. */
        public static final String TRANSACTION_REQ_CHECK_RESULT = "TRANSACTION_REQUIREMENTS_CHECK_RESULT";
        /** Delivery address value argument. */
        public static final String DELIVERY_ADDRESS_VALUE = "DELIVERY_ADDRESS_VALUE";
        /** Transactions decision argument. */
        public static final String TRANSACTION_DECISION_VALUE = "TRANSACTION_DECISION_VALUE";
        /** Confirmation argument. */
        public static final String CONFIRMATION = "CONFIRMATION";
        /** DateTime argument. */
        public static final String DATETIME = "DATETIME";
        /** Sign in status argument. */
        public static final String SIGN_IN = "SIGN_IN";
        /** Reprompt count for consecutive NO_INPUT intents. */
        public static final String REPROMPT_COUNT = "REPROMPT_COUNT";
        /** Flag representing finality of NO_INPUT intent. */
        public static final String IS_FINAL_REPROMPT = "IS_FINAL_REPROMPT";
        /** New surface value argument. */
        public static final String NEW_SURFACE = "NEW_SURFACE";

    }

    /**
     * List of built-in value type names.
     */
    public class InputValueDataTypes {
        /** Permission Value Spec. */
        public static final String PERMISSION = "type.googleapis.com/google.actions.v2.PermissionValueSpec";
        /** Option Value Spec. */
        public static final String OPTION = "type.googleapis.com/google.actions.v2.OptionValueSpec";
        /** Transaction Requirements Check Value Spec. */
        public static final String TRANSACTION_REQ_CHECK = "type.googleapis.com/google.actions.v2.TransactionRequirementsCheckSpec";
        /** Delivery Address Value Spec. */
        public static final String DELIVERY_ADDRESS = "type.googleapis.com/google.actions.v2.DeliveryAddressValueSpec";
        /** Transaction Decision Value Spec. */
        public static final String TRANSACTION_DECISION = "type.googleapis.com/google.actions.v2.TransactionDecisionValueSpec";
        /** Confirmation Value Spec. */
        public static final String CONFIRMATION = "type.googleapis.com/google.actions.v2.ConfirmationValueSpec";
        /** DateTime Value Spec. */
        public static final String DATETIME = "type.googleapis.com/google.actions.v2.DateTimeValueSpec";
        /** New Surface Value Spec. */
        public static final String NEW_SURFACE = "type.googleapis.com/google.actions.v2.NewSurfaceValueSpec";
        /** Register Update Value Spec. */
        public static final String REGISTER_UPDATE = "type.googleapis.com/google.actions.v2.RegisterUpdateValueSpec";
    }

    /**
     * List of possible conversation stages, as defined in the
     */
    public class ConversationStages {
        /**
         * Unspecified conversation state.
         */
        public static final String UNSPECIFIED = "UNSPECIFIED";
        /**
         * A new conversation.
         */
        public static final String NEW ="NEW";
        /**
         * An active (ongoing) conversation.
         */
        public static final String ACTIVE = "ACTIVE";
        
    }

    /**
     * List of possible user input types.
     */
    public class InputTypes {
        public static final String UNSPECIFIED = "UNSPECIFIED";
        public static final String TOUCH = "TOUCH";
        public static final String VOICE = "VOICE";
        public static final String KEYBOARD = "KEYBOARD";
    }

    /**
     * List of surface capabilities supported by the app.
     */
    public class SurfaceCapabilities {
        public static final String AUDIO_OUTPUT = "actions.capability.AUDIO_OUTPUT";
        public static final String SCREEN_OUTPUT = "actions.capability.SCREEN_OUTPUT";
    }

    public class TimeContextFrequency {
        public static final String DAILY = "DAILY";
    }

    protected class Placeholders {
        public static final String PERMISSION = "PLACEHOLDER_FOR_PERMISSION";
        public static final String TXN_DECISION = "PLACEHOLDER_FOR_TXN_DECISION";
        public static final String TXN_REQUIREMENTS = "PLACEHOLDER_FOR_TXN_REQUIREMENTS";
        public static final String DELIVERY_ADDRESS = "PLACEHOLDER_FOR_DELIVERY_ADDRESS";
        public static final String SIGN_IN = "PLACEHOLDER_FOR_SIGN_IN";
        public static final String CONFIRMATION = "PLACEHOLDER_FOR_CONFIRMATION";
        public static final String DATETIME = "PLACEHOLDER_FOR_DATETIME";
        public static final String NEW_SURFACE = "PLACEHOLDER_FOR_NEW_SURFACE";
        public static final String REGISTER_UPDATE = "PLACEHOLDER_FOR_REGISTER_UPDATE";
    }

    /**
     * Constructs RichResponse with chainable property setters.
     *
     * @return {@link RichResponse}
     */
    public RichResponse buildRichResponse() {
        return new RichResponse();
    }

    /**
     * Constructs RichResponse with chainable property setters.
     *
     * @param richResponse {@link RichResponse} object
     * @return {@link RichResponse}
     */
    public RichResponse buildRichResponse(RichResponse richResponse) {
        return new RichResponse(richResponse);
    }

    /**
     * Constructs BasicCard with chainable property setters.
     *
     * @param bodyText {@link String} as body text
     * @return {@link BasicCard}
     */
    public BasicCard buildBasicCard(String bodyText) {
        BasicCard card = new BasicCard();
        if (!Util.isNullOrEmpty(bodyText)) {
            card.setBodyText(bodyText);
        }
        return card;
    }

    /**
     * Constructs List with chainable property setters.
     *
     * @param title {@link String} as title
     * @return {@link ListSelect}
     */
    public ListSelect buildList(String title) {
        return new ListSelect(title);
    }

    /**
     * Constructs Carousel with chainable property setters.
     *
     * @return {@link Carousel}
     */
    public Carousel buildCarousel() {
        return new Carousel();
    }

    /**
     * Constructs OptionItem with chainable property setters.
     *
     * @param key {@link String}
     * @param synonyms {@link Object} it can be ArrayList, String or String []
     * @return {@link ca.sukhsingh.actions.on.google.response.data.google.systemintent.Item}
     */
    public Item buildOptionItem(String key, Object synonyms) {
        Item item = new Item();
        item.setKey(key);
        item.setSynonyms(synonyms);
        return item;
    }

    /*
    buildCart
    buildLineItem
    buildOrder
    buildOrderUpdate

     */

    /**
     * <h2>Ask for Permission</h2>
     * Equivalent to {@link AssistantApp#askForPermission(String, String) askForPermission},
     * but allows you to prompt the user for more than one permission at once.
     *
     * Notes:
     *
     * * The order in which you specify the permission prompts does not matter -
     *   it is controlled by the Assistant to provide a consistent user experience.
     * * The user will be able to either accept all permissions at once, or none.
     *   If you wish to allow them to selectively accept one or other, make several
     *   dialog turns asking for each permission independently with askForPermission.
     * * Asking for DEVICE_COARSE_LOCATION and DEVICE_PRECISE_LOCATION at once is
     *   equivalent to just asking for DEVICE_PRECISE_LOCATION
     *
     *
     * @param context {@link String} context Context why the permission is being asked; it's the TTS
     *     prompt prefix (action phrase) we ask the user.
     * @param permissions {@link java.util.List} permissions Array of permissions App supports, each of
     *     which comes from AssistantApp.SupportedPermissions.
     * @return {@link Response} A response is sent to Assistant to ask for the user's permission; for any
     *     invalid input, we return null.
     */
    public Response askForPermissions(String context, List<String> permissions) {
        debug("askForPermissions: context=%s, permissions=%s", context, stringify(permissions));
        if (Util.isNullOrEmpty(context)) {
            logger.error("invalid context");
            return null;
        }

        if (Util.isNull(permissions) || permissions.size() == 0) {
            logger.error("invalid permissions");
            return null;
        }

        for (String permission : permissions) {
            if ( !permission.equals(SupportedPermissions.NAME) &&
                 !permission.equals(SupportedPermissions.DEVICE_PRECISE_LOCATION) &&
                 !permission.equals(SupportedPermissions.DEVICE_COARSE_LOCATION) ) {
                logger.error("invalid permission type");
                return null;
            }
        }
        //TODO dialog state

        SystemIntentData systemIntentData = new SystemIntentData(context, permissions);
        return fulfillPermissionsRequest(systemIntentData,null);
    }

    /**
     * Asks the Assistant to guide the user to grant a permission. For example,
     * if you want your app to get access to the user's name, you would invoke
     * the askForPermission method with a context containing the reason for the request,
     * and the AssistantApp.SupportedPermissions.NAME permission. With this, the Assistant will ask
     * the user, in your agent's voice, the following: '[Context with reason for the request],
     * I'll just need to get your name from Google, is that OK?'.
     *
     * Once the user accepts or denies the request, the Assistant will fire another intent:
     * assistant.intent.action.PERMISSION with a boolean argument: AssistantApp.BuiltInArgNames.PERMISSION_GRANTED
     * and, if granted, the information that you requested.
     *
     * @see <a href="https://developers.google.com/actions/reference/conversation#ExpectedIntent">ExpectedIntent</a>
     *
     *
     * @param context context Context why the permission is being asked; it's the TTS
     *     prompt prefix (action phrase) we ask the user.
     * @param permission Array of permissions App supports, each of
     *     which comes from AssistantApp.SupportedPermissions.
     * @return Response {@link Response} DialogFlowResponse Object or null
     */
    public Response askForPermission(String context, String permission) {
        debug("askForPermission: context=%s, permissions=%s",context,permission);
        if (Util.isNullOrEmpty(context)) {
            logger.error("invalid context");
            return null;
        }

        if (Util.isNullOrEmpty(permission)) {
            logger.error("invalid context");
            return null;
        }
        List<String> permissions = new ArrayList<>();
        permissions.add(permission);
        return askForPermissions(context, permissions);
    }

    /**
     * Asks user for a confirmation, with dialogState.
     * <br>
     * <br>
     * Example
     * <pre>
     * {@code
     * DialogflowApp app = new DialogflowApp();
     *
     * public Response askForUserConfirmation() {
     *      return app.askForConfirmation("Would you like to do this ?");
     * }
     * }
     * </pre>
     * @param prompt {@link String} prompt The confirmation prompt presented to the user to
     *     query for an affirmative or negative response. If undefined or null,
     *     Google will use a generic yes/no prompt.
     * @param dialogState {@link Object} JSON object the app uses to hold dialog state that
     *     will be circulated back by Assistant.
     * @return Response {@link Response} DialogFlowResponse Object or null
     */
    public Response askForConfirmation(String prompt, Object dialogState) {
        debug("askForConfirmation: prompt=%s & dialogState=%s",prompt, stringify(dialogState));
        SystemIntentData data = new SystemIntentData();
        DialogSpec confirmationValueSpec = new DialogSpec();
        if (Util.isNull(prompt)) {
            return null;
        }
        confirmationValueSpec.setRequestConfirmationText(prompt);
        data.setDialogSpec(confirmationValueSpec);
        return fulfillSystemIntent(
                StandardIntents.CONFIRMATION,
                InputValueDataTypes.CONFIRMATION,
                data,
                Placeholders.CONFIRMATION,
                dialogState);
    }

    /**
     * Asks user for a confirmation.
     * <br>
     * <br>
     * Example
     * <pre>
     * {@code
     * DialogflowApp app = new DialogflowApp();
     *
     * public Response askForUserConfirmation() {
     *      return app.askForConfirmation("Would you like to do this ?");
     * }
     * }
     * </pre>
     *
     * @param prompt {@link String} prompt The confirmation prompt presented to the user to
     *     query for an affirmative or negative response. If undefined or null,
     *     Google will use a generic yes/no prompt.
     * @return Response {@link Response} DialogFlowResponse Object or null
     */
    public Response askForConfirmation(String prompt) {
        debug("askForConfirmation: prompt=%s",prompt);
        return askForConfirmation(prompt,null);
    }

    /**
     * Asks user for a timezone-agnostic date and time.
     * <br>
     * <br>
     * Example
     * <pre>
     * {@code
     * DialogflowApp app = new DialogflowApp();
     *
     * public Response askForAppointmentTime() {
     *      return app.askForDateTime("When do you want to come in?",
     *                               "Which date works best for you?",
     *                               "What time of day works best for you?");
     * }
     * }
     * </pre>
     *
     * @param initialPrompt {@link String} The initial prompt used to ask for a
     *     date and time. If undefined or null, Google will use a generic
     *     prompt.
     * @param datePrompt {@link String} The prompt used to specifically ask for the
     *     date if not provided by user. If undefined or null, Google will use a
     *     generic prompt.
     * @param timePrompt {@link String} The prompt used to specifically ask for the
     *     time if not provided by user. If undefined or null, Google will use a
     *     generic prompt.
     *
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForDateTime(String initialPrompt,String datePrompt,String timePrompt) {
        debug("askForDateTime: initialPrompt=%s datePrompt=%s timePrompt=%s",initialPrompt,datePrompt,timePrompt);
        return askForDateTime(initialPrompt,datePrompt,timePrompt,null);
    }

    /**
     * Asks user for a timezone-agnostic date and time, with dialogState
     *
     * <br>
     * <br>
     * Example
     * <pre>
     * {@code
     * DialogflowApp app = new DialogflowApp();
     *
     * public Response askForAppointmentTime() {
     *      return app.askForDateTime("When do you want to come in?",
     *                               "Which date works best for you?",
     *                               "What time of day works best for you?");
     * }
     * }
     * </pre>
     *
     * @param initialPrompt {@link String} The initial prompt used to ask for a
     *     date and time. If undefined or null, Google will use a generic
     *     prompt.
     * @param datePrompt {@link String} The prompt used to specifically ask for the
     *     date if not provided by user. If undefined or null, Google will use a
     *     generic prompt.
     * @param timePrompt {@link String} The prompt used to specifically ask for the
     *     time if not provided by user. If undefined or null, Google will use a
     *     generic prompt.
     * @param dialogState {@link Object} JSON object the app uses to hold dialog state that
     *     will be circulated back by Assistant.
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForDateTime(String initialPrompt,String datePrompt,String timePrompt,Object dialogState) {
        debug("askForDateTime: initialPrompt=%s datePrompt=%s timePrompt=%s dialogState=%s",initialPrompt,datePrompt,timePrompt,stringify(dialogState));
        DialogSpec confirmationValueSpec = new DialogSpec();
        confirmationValueSpec.setRequestDatetimeText(initialPrompt);
        confirmationValueSpec.setRequestDateText(datePrompt);
        confirmationValueSpec.setRequestTimeText(timePrompt);
        SystemIntentData data = new SystemIntentData();
        data.setDialogSpec(confirmationValueSpec);
        return fulfillSystemIntent(
                StandardIntents.DATETIME,
                InputValueDataTypes.DATETIME,
                data,
                Placeholders.DATETIME,
                dialogState);
    }

    /**
     * Requests the user to switch to another surface during the conversation.
     *
     * @param context {@link String} context Context why new surface is requested; it's the TTS
     *     prompt prefix (action phrase) we ask the user.
     * @param notificationTitle {@link String} notificationTitle Title of the notification appearing on
     *     new surface device.
     *
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForNewSurface(String context,String notificationTitle,List<String> capabilities) {
        debug("askForNewSurface: context=%s notificationTitle=%s capabilities=%s", context,notificationTitle,stringify(capabilities));
        return askForNewSurface(context,notificationTitle,capabilities,null);
    }

    /**
     * Requests the user to switch to another surface during the conversation, with dialogState
     *
     * @param context {@link String} context Context why new surface is requested; it's the TTS
     *     prompt prefix (action phrase) we ask the user.
     * @param notificationTitle {@link String} notificationTitle Title of the notification appearing on
     *     new surface device.
     * @param dialogState {@link Object} JSON object the app uses to hold dialog state that
     *     will be circulated back by Assistant.
     *
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForNewSurface(String context,String notificationTitle,List<String> capabilities, Object dialogState) {
        debug("askForNewSurface: context=%s notificationTitle=%s capabilities=%s dialogState=%s",
                context,notificationTitle,stringify(capabilities),stringify(dialogState));
        SystemIntentData data = new SystemIntentData(context,notificationTitle,capabilities);
        return fulfillSystemIntent(
                StandardIntents.NEW_SURFACE,
                InputValueDataTypes.NEW_SURFACE,
                data,
                Placeholders.NEW_SURFACE,
                dialogState
        );
    }

    /**
     * Asks user for delivery address, with dialogState
     *
     * @param reason {@link String} Reason given to user for asking delivery address.
     * @param dialogState {@link Object} JSON object the app uses to hold dialog state that
     *     will be circulated back by Assistant.
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForDeliveryAddress(String reason, Object dialogState) {
        debug("askForDeliveryAddress: reason=%s dialogState=%s", reason,stringify(dialogState));
        AddressOptions addressOptions = new AddressOptions();
        addressOptions.setReason(reason);
        SystemIntentData data = new SystemIntentData();
        data.setAddressOptions(addressOptions);

        return fulfillSystemIntent(
                StandardIntents.DELIVERY_ADDRESS,
                InputValueDataTypes.DELIVERY_ADDRESS,
                data,
                Placeholders.DELIVERY_ADDRESS,
                dialogState
        );

    }

    /**
     * Asks user for delivery address.
     *
     * @param reason {@link String} Reason given to user for asking delivery address.
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForDeliveryAddress(String reason) {
        debug("askForDeliveryAddress: reason=%s", reason);
        return askForDeliveryAddress(reason,null);
    }

    /**
     * Hands the user off to a web sign in flow. App sign in and OAuth credentials
     * are set in the Action On Google Console
     *
     * Retrieve the access token in subsequent intents using
     * app.getUser().accessToken.
     *
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForSignIn() {
        return askForSignIn(null);
    }

    /**
     * Hands the user off to a web sign in flow. App sign in and OAuth credentials
     * are set in the Action On Google Console
     *
     * Retrieve the access token in subsequent intents using
     * app.getUser().accessToken.
     *
     * @param dialogState {@link Object} JSON object the app uses to hold dialog state that
     *     will be circulated back by Assistant.
     * @return {@link Response} DialogFlowResponse Object or null
     */
    public Response askForSignIn(Object dialogState) {
        return fulfillSystemIntent(
                StandardIntents.SIGN_IN,
                null,
                null,
                Placeholders.SIGN_IN,
                dialogState
        );
    }

    public Response askForTransactionDecision() {
        return null;
    }

    public Response askForTransactionRequirements() {
        return null;
    }

    /**
     * Prompts the user for permission to send proactive updates at any time.
     *
     * @param intent If using Dialogflow, the action name of the intent
     *     to be triggered when the update is received. If using Actions SDK, the
     *     intent name to be triggered when the update is received.
     * @param intentArguments The necessary arguments
     *     to fulfill the intent triggered on update.
     * @param dialogState JSON object the app uses to hold dialog state that
     *     will be circulated back by Assistant.
     * @return A response is sent to Assistant to ask for the user's permission; for any
     *     invalid input, we return null.
     */
    public Response askForUpdatePermission(String intent,List<IntentArgument> intentArguments,Object dialogState) {
        debug("askForUpdatePermission: intent=%s intentArguments=%s dialogState=%s",
                intent,stringify(intentArguments),stringify(dialogState));
        if (isNullOrEmpty(intent)) {
            logger.error("Name of intent to trigger on update must be specified");
            return null;
        }
        UpdatePermissionValueSpec updatePermissionValueSpec = new UpdatePermissionValueSpec(intent);

        if (isNotNull(intentArguments)) {
            updatePermissionValueSpec.setArguments(intentArguments);
        }
        List<String> permissions = new ArrayList<>();
        permissions.add(SupportedPermissions.UPDATE);
        SystemIntentData data = new SystemIntentData();
        data.setPermissions(permissions);
        data.setUpdatePermissionValueSpec(updatePermissionValueSpec);
        return fulfillPermissionsRequest(data,dialogState);
    }

    public Response askToRegisterDailyUpdate(String intent,List<IntentArgument> intentArguments,Object dialogState) {
        debug("askToRegisterDailyUpdate: intent=%s intentArguments=%s dialogState=%s",
                intent,stringify(intentArguments),stringify(dialogState));
        if (isNullOrEmpty(intent)) {
            logger.error("Name of intent to trigger on update must be specified");
            return null;
        }

        SystemIntentData data = new SystemIntentData();
        data.setIntent(intent);
        TriggerContext triggerContext = new TriggerContext();
        TimeContext timeContext = new TimeContext(TimeContextFrequency.DAILY);
        triggerContext.setTimeContext(timeContext);
        data.setTriggerContext(triggerContext);
        if (isNotNull(intentArguments)) {
            data.setArguments(intentArguments);
        }

        return fulfillSystemIntent(
                StandardIntents.REGISTER_UPDATE,
                InputValueDataTypes.REGISTER_UPDATE,
                data,
                Placeholders.REGISTER_UPDATE,
                dialogState
        );
    }

    Response fulfillPermissionsRequest(SystemIntentData systemIntentData,Object dialogState) {return null;}
    Response fulfillSystemIntent(String intent, String specType, SystemIntentData intentSpec, String promptPlaceholder, Object dialogState) {return null;}

    private void debug(String format, Object... args) {
        logger.debug(String.format(format,args));
    }
}
