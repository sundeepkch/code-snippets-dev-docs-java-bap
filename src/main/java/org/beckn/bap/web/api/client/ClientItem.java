package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientItem {
    private String itemId;
    private String itemCode;
    private String itemName;
    private String minPrice;
    private String maxPrice;
}
