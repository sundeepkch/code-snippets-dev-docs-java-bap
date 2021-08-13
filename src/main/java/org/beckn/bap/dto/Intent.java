package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Intent {
    private Provider provider;
    private Fulfillment fulfillment;
    private Payment payment;
    private Category category;
    private Offer offer;
    private Item item;
    private String purpose;
    private Tags tags;

}
