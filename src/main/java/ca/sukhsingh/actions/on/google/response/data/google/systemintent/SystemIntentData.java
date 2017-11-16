
package ca.sukhsingh.actions.on.google.response.data.google.systemintent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sukhSingh on 2017-08-09.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemIntentData {

    @JsonProperty("@type")
    private String type;
    @JsonProperty("intent")
    public String intent;
    @JsonProperty("optContext")
    private String optContext;
    @JsonProperty("permissions")
    private List<String> permissions ;
    @JsonProperty("listSelect")
    private ListSelect listSelect;
    @JsonProperty("carouselSelect")
    private Carousel carousel;
    @JsonProperty("dialogSpec")
    private DialogSpec dialogSpec;
    @JsonProperty("context")
    public String context;
    @JsonProperty("notificationTitle")
    public String notificationTitle;
    @JsonProperty("capabilities")
    public List<String> capabilities = null;
    @JsonProperty("addressOptions")
    public AddressOptions addressOptions;
    @JsonProperty("triggerContext")
    public TriggerContext triggerContext;
    @JsonProperty("updatePermissionValueSpec")
    public UpdatePermissionValueSpec updatePermissionValueSpec;
    @JsonProperty("arguments")
    public List<IntentArgument> arguments;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public SystemIntentData() {
    }

    public SystemIntentData(String optContext, List<String> permissions) {
        this.optContext = optContext;
        this.permissions = permissions;
    }

    public SystemIntentData(String context, String notificationTitle,List<String> capabilities) {
        this.context = context;
        this.notificationTitle = notificationTitle;
        this.capabilities = capabilities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptContext() {
        return optContext;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public ListSelect getListSelect() {
        return listSelect;
    }

    public void setListSelect(ListSelect listSelect) {
        this.listSelect = listSelect;
    }

    public Carousel getCarousel() {
        return carousel;
    }

    public void setCarousel(Carousel carousel) {
        this.carousel = carousel;
    }

    public DialogSpec getDialogSpec() {
        return dialogSpec;
    }

    public void setDialogSpec(DialogSpec dialogSpec) {
        this.dialogSpec = dialogSpec;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public AddressOptions getAddressOptions() {
        return addressOptions;
    }

    public void setAddressOptions(AddressOptions addressOptions) {
        this.addressOptions = addressOptions;
    }

    public void setTriggerContext(TriggerContext triggerContext) {
        this.triggerContext = triggerContext;
    }

    public void setUpdatePermissionValueSpec(UpdatePermissionValueSpec updatePermissionValueSpec) {
        this.updatePermissionValueSpec = updatePermissionValueSpec;
    }

    public void setArguments(List<IntentArgument> arguments) {
        this.arguments = arguments;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
