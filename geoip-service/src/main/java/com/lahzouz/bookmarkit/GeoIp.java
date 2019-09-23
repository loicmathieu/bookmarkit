package com.lahzouz.bookmarkit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoIp {
    public String ip;
    @JsonProperty("country_code")
    public String countryCode;
    @JsonProperty("country_name")
    public String countryName;
    @JsonProperty("region_code")
    public String regionCode;
    @JsonProperty("region_name")
    public String regionName;
    public String city;
    @JsonProperty("zip_code")
    public String zipCode;
    @JsonProperty("time_zone")
    public String timeZone;
    public Double latitude;
    public Double longitude;
    @JsonProperty("metro_code")
    public Integer metroCode;
}
