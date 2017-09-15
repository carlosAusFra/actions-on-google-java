package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sukhsingh on 2017-08-18.
 */
public class PostalAddress {

    @JsonProperty("revision")
    private int revision;

    @JsonProperty("regionCode")
    private String regionCode;

    @JsonProperty("languageCode")
    private String languageCode;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("sortingCode")
    private String sortingCode;

    @JsonProperty("administrativeArea")
    private String administrativeArea;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("sublocality")
    private String sublocality;

    @JsonProperty("addressLines")
    private List<String> addressLines;

    @JsonProperty("recipients")
    private List<String> recipients;

    @JsonProperty("organization")
    private String organization;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getRevision() {
        return revision;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getSortingCode() {
        return sortingCode;
    }

    public String getAdministrativeArea() {
        return administrativeArea;
    }

    public String getLocality() {
        return locality;
    }

    public String getSublocality() {
        return sublocality;
    }

    public List<String> getAddressLines() {
        return addressLines;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getOrganization() {
        return organization;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
