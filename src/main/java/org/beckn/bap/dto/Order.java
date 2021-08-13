package org.beckn.bap.dto;

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
public class Order {
    private String id;
    private String state;
    private List<OrderItem> items;
    @JsonProperty("add_ons")
    private List<AddOn> addOns;
    private List<Offer> offers;
    private Billing billing;
    private Fulfillment fulfillment;
    private Quotation quote;
    private Payment payment;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

}
