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
public class Address {
    private String door;
    private String name;
    private String building;
    private String street;
    private String locality;
    private String ward;
    private String city;
    private String state;
    private String country;
    @JsonProperty("area_code")
    private String areaCode;
}
