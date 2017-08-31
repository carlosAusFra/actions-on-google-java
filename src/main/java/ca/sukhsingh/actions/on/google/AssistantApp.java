package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.data.google.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.RichResponse;

/**
 * Created by sinsukhv on 2017-08-31.
 */
public class AssistantApp {

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
}
