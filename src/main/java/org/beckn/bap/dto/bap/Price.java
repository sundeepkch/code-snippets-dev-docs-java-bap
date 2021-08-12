package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {
    private String currency;
    private DecimalValue value;
    @JsonProperty("estimated_value")
    private DecimalValue estimatedValue;
    @JsonProperty("computed_value")
    private DecimalValue computedValue;
    @JsonProperty("listed_value")
    private DecimalValue listedValue;
    @JsonProperty("offered_value")
    private DecimalValue offeredValue;
    @JsonProperty("minimum_value")
    private DecimalValue minimumValue;
    @JsonProperty("maximum_value")
    private DecimalValue maximumValue;


}
