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
public class ItemQuantity {
    private Integer count;
    private ItemQuantityAllocated allocated;
    private ItemQuantityAllocated available;
    private ItemQuantityAllocated maximum;
    private ItemQuantityAllocated minimum;
    private ItemQuantityAllocated selected;
}
