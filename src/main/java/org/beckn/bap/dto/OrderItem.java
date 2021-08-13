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
public class OrderItem {
    private String id;
    @JsonProperty("parent_item_id")
    private String parentItemId;
    private Descriptor descriptor;
    private Price price;
    @JsonProperty("category_id")
    private String categoryId;
    @JsonProperty("location_id")
    private String locationId;
    private Time time;
    private Boolean matched;
    private Boolean related;
    private Boolean recommended;
    private Tags tags;
    private ItemQuantity quantity;
}
