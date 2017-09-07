
package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sukhSingh on 2017-08-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemIntentData {

    public SystemIntentData(String optContext, List<String> permissions) {
        this.optContext = optContext;
        this.permissions = permissions;
    }

    public SystemIntentData() {
    }

    @JsonProperty("@type")
    private String type;
    @JsonProperty("optContext")
    private String optContext;
    @JsonProperty("permissions")
    private List<String> permissions ;
    @JsonProperty("listSelect")
    private ca.sukhsingh.actions.on.google.response.data.google.systemintent.List listSelect;
    @JsonProperty("carouselSelect")
    private Carousel carousel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptContext() {
        return optContext;
    }

    public void setOptContext(String optContext) {
        this.optContext = optContext;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public ca.sukhsingh.actions.on.google.response.data.google.systemintent.List getListSelect() {
        return listSelect;
    }

    public void setListSelect(ca.sukhsingh.actions.on.google.response.data.google.systemintent.List listSelect) {
        this.listSelect = listSelect;
    }

    public Carousel getCarousel() {
        return carousel;
    }

    public void setCarousel(Carousel carousel) {
        this.carousel = carousel;
    }
}
