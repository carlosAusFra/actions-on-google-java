package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.*;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhsingh on 2017-08-31.
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
     *
     * @return {@link RichResponse}
     */
    public RichResponse buildRichResponse() {
        return new RichResponse();
    }

    /**
     *
     * @param richResponse {@link RichResponse} object
     * @return {@link RichResponse}
     */
    public RichResponse buildRichResponse(RichResponse richResponse) {
        return new RichResponse(richResponse);
    }

    /**
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
     *
     * @param title {@link String} as title
     * @return {@link ListSelect}
     */
    public ListSelect buildList(String title) {
        return new ListSelect(title);
    }

    public Carousel buildCarousel() {
        return new Carousel();
    }

    /**
     *
     * @param key {@link String}
     * @param synonyms {@link Object} it can be ArrayList, String or String []
     * @return ca.sukhsingh.actions.on.google.response.data.google.systemintent.Item
     */
    public Item buildOptionItem(String key, Object synonyms) {
        Item item = new Item();
        item.setKey(key);
        item.setSynonyms(synonyms);
        return item;
    }

    /**
     *
     * @param context {@link String}
     * @param permissions {@link java.util.List} list of permissions
     * @return Response
     */
    public Response askForPermissions(String context, List<String> permissions) {
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
        return fulfillPermissionsRequest(systemIntentData);
    }

    public Response askForPermission(String context, String permission) {
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
     * Asks user for a confirmation.
     *
     * @param prompt {@link String} prompt The confirmation prompt presented to the user to
     *     query for an affirmative or negative response. If undefined or null,
     *     Google will use a generic yes/no prompt.
     * @param dialogState {@link Object} JSON object the app uses to hold dialog state that
     *     will be circulated back by Assistant.
     * @return Response {@link Response} DialogFlowResponse Object or null
     */
    public Response askForConfirmation(String prompt, Object dialogState) {
        DialogSpec confirmationValueSpec = new DialogSpec();
        if (Util.isNull(prompt)) {
            return null;
        }
        confirmationValueSpec.setRequestConfirmationText(prompt);
        return fulfillSystemIntent(
                StandardIntents.CONFIRMATION,
                InputValueDataTypes.CONFIRMATION,
                confirmationValueSpec,
                "PLACEHOLDER_FOR_CONFIRMATION",
                dialogState);
    }

    /**
     * Asks user for a confirmation.
     *
     * Example
     * app.askForConfirmation("Would you like to do this ?");
     *
     * @param prompt {@link String} prompt The confirmation prompt presented to the user to
     *     query for an affirmative or negative response. If undefined or null,
     *     Google will use a generic yes/no prompt.
     * @return Response {@link Response} DialogFlowResponse Object or null
     */
    public Response askForConfirmation(String prompt) {
        return askForConfirmation(prompt,null);
    }

    Response fulfillPermissionsRequest(SystemIntentData systemIntentData) {return null;}
    Response fulfillSystemIntent(String intent, String specType, DialogSpec intentSpec, String promptPlaceholder, Object dialogState) {return null;}
}
