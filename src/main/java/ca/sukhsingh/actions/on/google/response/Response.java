
package ca.sukhsingh.actions.on.google.response;

import ca.sukhsingh.actions.on.google.Util;
import ca.sukhsingh.actions.on.google.response.data.google.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhSingh on 2017-08-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    @JsonProperty("speech")
    private String speech;
    @JsonProperty("contextOut")
    private List<ContextOut> contextOut ;
    @JsonProperty("data")
    private Data data;

    public Response() {
        this.contextOut = new ArrayList<>();
        this.data = new Data();
    }

    public Response(Data data) {
        this.data = data;
    }

    public Response(Response response) {

        this.contextOut = new ArrayList<>();
        this.data = new Data();

        if (Util.isNotNull(response)){
            if (!Util.isNullOrEmpty(response.speech)) {
                this.speech = response.speech;
            }

            if (!Util.isNull(response.contextOut)){
                this.contextOut = response.contextOut;
            }

            if (!Util.isNull(response.data)) {
                this.data = data;
            }
        }
    }

    public Response addContextOut(ContextOut contextOut) {
        this.contextOut.add(contextOut);
        return this;
    }

    public Response addContextOut(String context, int lifeSpan) {
        ContextOut contextOut = new ContextOut(context, lifeSpan, null);
        this.contextOut.add(contextOut);
        return this;
    }

    public Response addContextOut(String context, int lifeSpan, Parameters parameters) {
        ContextOut contextOut = new ContextOut(context, lifeSpan, parameters);
        this.contextOut.add(contextOut);
        return this;
    }

    public Response addContextOut(String context, int lifeSpan, String key, String value) {
        if (Util.isNullOrEmpty(key)) {
            return null;
        }
        if (Util.isNullOrEmpty(value)) {
            return null;
        }
        Parameters parameters = new Parameters(key,value);
        ContextOut contextOut = new ContextOut(context, lifeSpan, parameters);
        this.contextOut.add(contextOut);
        return this;
    }

    public Response addContextOut(String context, int lifeSpan, String key, Object value) {
        if (Util.isNullOrEmpty(key)) {
            return null;
        }
        if (Util.isNull(value)) {
            return null;
        }
        Parameters parameters = new Parameters(key,value);
        ContextOut contextOut = new ContextOut(context, lifeSpan, parameters);
        this.contextOut.add(contextOut);
        return this;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public List<ContextOut> getContextOut() {
        return contextOut;
    }

    public Response addContextOuts(List<ContextOut> contextOut) {
        this.contextOut = contextOut;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
