
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class RawInput {

    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("query")
    private String query;
    @JsonProperty("inputType")
    private String inputType;

    public String getCreateTime() {
        return createTime;
    }

    public String getQuery() {
        return query;
    }

    public String getInputType() {
        return inputType;
    }
}
