package ca.sukhsingh.actions.on.google.response.data.google.RichResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhsingh on 2017-08-14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    public Item(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
    }

    public Item(BasicCard basicCard) {
        this.basicCard = basicCard;
    }

    public Item(StructuredResponse structuredResponse) {
        this.structuredResponse = structuredResponse;
    }

    @JsonProperty("simpleResponse")
    private SimpleResponse simpleResponse;

    @JsonProperty("basicCard")
    private BasicCard basicCard;

    @JsonProperty("structuredResponse")
    private StructuredResponse structuredResponse;

    public SimpleResponse getSimpleResponse() {
        return simpleResponse;
    }

    public void setSimpleResponse(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
    }

    public BasicCard getBasicCard() {
        return basicCard;
    }

    public void setBasicCard(BasicCard basicCard) {
        this.basicCard = basicCard;
    }

    public StructuredResponse getStructuredResponse() {
        return structuredResponse;
    }

    public void setStructuredResponse(StructuredResponse structuredResponse) {
        this.structuredResponse = structuredResponse;
    }

    public class StructuredResponse {

        @JsonProperty("orderUpdate")
        private OrderUpdate orderUpdate;

        public OrderUpdate getOrderUpdate() {
            return orderUpdate;
        }

        public void setOrderUpdate(OrderUpdate orderUpdate) {
            this.orderUpdate = orderUpdate;
        }
    }

    //TODO still have to do it
    public class OrderUpdate {

    }
}
