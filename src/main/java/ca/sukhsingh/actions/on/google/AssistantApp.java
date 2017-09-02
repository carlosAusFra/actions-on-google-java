package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.SystemIntentData;
import ca.sukhsingh.actions.on.google.response.data.google.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.Google;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse;

import java.util.List;

/**
 * Created by sinsukhv on 2017-08-31.
 */
public class AssistantApp {

    public class SupportedPermissions {
        public static final String NAME = "NAME";
        public static final String DEVICE_PRECISE_LOCATION = "DEVICE_PRECISE_LOCATION";
        public static final String DEVICE_COARSE_LOCATION = "DEVICE_PRECISE_LOCATION";
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
