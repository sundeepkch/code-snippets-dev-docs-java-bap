package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    private String id;
    private Descriptor descriptor;
    private String gps;
    private Address address;
    @JsonProperty("station_code")
    private String stationCode;
    private City city;
    private Country country;
    private Circle circle;
    private String polygon;
    private String _3dspace;
    private Time time;
}
