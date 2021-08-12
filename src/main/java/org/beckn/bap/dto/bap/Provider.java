package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Provider {
    private String id;
    private Descriptor descriptor;
    @JsonProperty("category_id")
    private String categoryId;
    private Time time;
    private List<Category> categories;
    private List<Fulfillment> fulfillments;
    private List<Payment> payments;
    private List<Location> locations;
    private List<Offer> offers;
    private List<Item> items;
    private OffsetDateTime exp;
    private Tags tags;
}
