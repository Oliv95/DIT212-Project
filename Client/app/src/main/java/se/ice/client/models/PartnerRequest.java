package se.ice.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Simon on 2016-05-25.
 */
public class PartnerRequest {

    @JsonProperty("from")
    private String from; // this user wants be a partner with

    @JsonProperty("to")
    private String to; // this user

    public PartnerRequest() {}

    public PartnerRequest(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartnerRequest that = (PartnerRequest) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        return !(to != null ? !to.equals(that.to) : that.to != null);

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
