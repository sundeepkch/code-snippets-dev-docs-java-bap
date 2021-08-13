package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vehicle {
    private String category;
    private Integer capacity;
    private String make;
    private String model;
    private String size;
    private String variant;
    private String color;
    private String energyType;
    private String registration;
}
