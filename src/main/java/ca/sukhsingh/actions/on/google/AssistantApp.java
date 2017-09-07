package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.Carousel;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.Item;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.SystemIntentData;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by sukhsingh on 2017-08-31.
 */
public class AssistantApp {

    Logger logger = Logger.getLogger(AssistantApp.class);

    public class SupportedPermissions {
        public static final String NAME = "NAME";
        public static final String DEVICE_PRECISE_LOCATION = "DEVICE_PRECISE_LOCATION";
        public static final String DEVICE_COARSE_LOCATION = "DEVICE_PRECISE_LOCATION";
    }

    public class StandardIntents {
        public static final String OPTION = "actions.intent.OPTION";
        public static final String PERMISSION = "actions.intent.PERMISSION";
    }

    public class InputValueDataTypes_ {
        public static final String OPTION = "type.googleapis.com/google.actions.v2.OptionValueSpec";
        public static final String PERMISSION = "type.googleapis.com/google.actions.v2.PermissionValueSpec";
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
     * @return {@link ca.sukhsingh.actions.on.google.response.data.google.systemintent.List}
     */
    public ca.sukhsingh.actions.on.google.response.data.google.systemintent.List buildList(String title) {
        return new ca.sukhsingh.actions.on.google.response.data.google.systemintent.List(title);
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
     * @param permissions {@link List} list of permissions
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

    Response fulfillPermissionsRequest(SystemIntentData systemIntentData) {return null;}
}
