package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    private String areaCode;
}
