package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.systemIntent.Item;
import ca.sukhsingh.actions.on.google.response.data.google.systemIntent.OptionInfo;
import ca.sukhsingh.actions.on.google.response.data.google.systemIntent.SystemIntentData;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse.RichResponse;

import java.util.List;

/**
 * Created by sukhsingh on 2017-08-31.
 */
public class AssistantApp {

    public class SupportedPermissions {
        public static final String NAME = "NAME";
        public static final String DEVICE_PRECISE_LOCATION = "DEVICE_PRECISE_LOCATION";
        public static final String DEVICE_COARSE_LOCATION = "DEVICE_PRECISE_LOCATION";
    }

    public class StandardIntents {
        public static final String OPTION = "actions.intent.OPTION";
    }

    public class InputValueDataTypes_ {
        public static final String OPTION = "type.googleapis.com/google.actions.v2.OptionValueSpec";
    }

    public RichResponse buildRichResponse() {
        return new RichResponse();
    }

    public RichResponse buildRichResponse(RichResponse richResponse) {
        return new RichResponse(richResponse);
    }

    public BasicCard buildBasicCard(String bodyText) {
        BasicCard card = new BasicCard();
        //TODO null check
        if (!Util.isNullOrEmpty(bodyText)) {
            card.setBodyText(bodyText);
        }
        return card;
    }

    public ca.sukhsingh.actions.on.google.response.data.google.systemIntent.List buildList(String title) {
        return new ca.sukhsingh.actions.on.google.response.data.google.systemIntent.List(title);
    }

    public Item buildOptionItem(String key, Object synonyms) {
        Item item = new Item();
        item.setKey(key);
        item.setSynonyms(synonyms);
        return item;
    }

    public Response askForPermissions(String context, List<String> permissions) {
        if (Util.isNullOrEmpty(context)) {
            // TODO ERROR logging
            return null;
        }

        if (Util.isNull(permissions) || permissions.size() == 0) {
            // TODO ERROR logging
            return null;
        }

        for (String permission : permissions) {
            if ( !permission.equals(SupportedPermissions.NAME) &&
                 !permission.equals(SupportedPermissions.DEVICE_PRECISE_LOCATION) &&
                 !permission.equals(SupportedPermissions.DEVICE_COARSE_LOCATION) ) {
                //TODO ERROR logging
                return null;
            }
        }
        //TODO dialog state

        SystemIntentData systemIntentData = new SystemIntentData(context, permissions);
        return fulfillPermissionsRequest_(systemIntentData);
    }

    Response fulfillPermissionsRequest_(SystemIntentData systemIntentData) {return null;}
}
