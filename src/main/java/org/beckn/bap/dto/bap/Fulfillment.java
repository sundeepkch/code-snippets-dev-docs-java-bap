package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fulfillment {
    private String id;
    private String type;
    @JsonProperty("provider_id")
    private String providerId;
    private State state;
    private Boolean tracking = false;
    private Customer customer;
    private Agent agent;
    private Vehicle vehicle;
    private FulfillmentStart start;
    private FulfillmentStart end;
    private String purpose;
    private List<Tags> tags;
}
