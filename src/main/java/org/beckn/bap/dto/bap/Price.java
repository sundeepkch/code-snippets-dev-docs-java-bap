package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Price {
    private String currency;
    private String value;

    @JsonProperty("estimated_value")
    @JsonFormat(pattern = "[+-]?([0-9]*[.])?[0-9]+")
    private String estimatedValue;

    @JsonProperty("computed_value")
    @JsonFormat(pattern = "[+-]?([0-9]*[.])?[0-9]+")
    private String computedValue;

    @JsonProperty("listed_value")
    @JsonFormat(pattern = "[+-]?([0-9]*[.])?[0-9]+")
    private String listedValue;

    @JsonProperty("offered_value")
    @JsonFormat(pattern = "[+-]?([0-9]*[.])?[0-9]+")
    private String offeredValue;

    @JsonProperty("minimum_value")
    @JsonFormat(pattern = "[+-]?([0-9]*[.])?[0-9]+")
    private String minimumValue;

    @JsonProperty("maximum_value")
    @JsonFormat(pattern = "[+-]?([0-9]*[.])?[0-9]+")
    private String maximumValue;

}
