package ca.sukhsingh.actions.on.google.response.data.google.richresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhsingh on 2017-08-14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suggestion {

    @JsonProperty("title")
    private String title;

    public Suggestion(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
