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
public class Price {
    private String currency;
    private String value;
    @JsonProperty("estimated_value")
    private String estimatedValue;
    @JsonProperty("computed_value")
    private String computedValue;
    @JsonProperty("listed_value")
    private String listedValue;
    @JsonProperty("offered_value")
    private String offeredValue;
    @JsonProperty("minimum_value")
    private String minimumValue;
    @JsonProperty("maximum_value")
    private String maximumValue;


}
