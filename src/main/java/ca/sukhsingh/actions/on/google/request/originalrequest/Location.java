
package ca.sukhsingh.actions.on.google.request.originalrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sukhSingh on 2017-08-09.
 */
public class Location {

    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("formattedAddress")
    private String formattedAddress;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("postalAddress")
    private PostalAddress postalAddress;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("notes")
    private String notes;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes;
    }
}
