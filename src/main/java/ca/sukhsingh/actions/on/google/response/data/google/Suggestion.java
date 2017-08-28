package ca.sukhsingh.actions.on.google.response.data.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sinsukhv on 2017-08-14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suggestion {

    public Suggestion(String title) {
        this.title = title;
    }

    @JsonProperty("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
