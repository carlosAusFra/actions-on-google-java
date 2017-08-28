
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
    }

    public Response(Response response) {

        this.contextOut = new ArrayList<>();
        this.data = new Data();

        if (Util.isNull(response)){
            if (!Util.isNullOrEmpty(response.speech)) {
                this.speech = response.speech;
            }

            if (!Util.isNull(response.contextOut)){
                this.contextOut = contextOut;
            }

            if (!Util.isNull(response.data)) {
                this.data = data;
            }
        }
    }

    public void addContextOut(ContextOut contextOut) {
        this.contextOut.add(contextOut);
    }

    public void addContextOuts(List<ContextOut> contextOuts) {
        this.contextOut.addAll(contextOuts);
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

    public void setContextOut(List<ContextOut> contextOut) {
        this.contextOut = contextOut;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
