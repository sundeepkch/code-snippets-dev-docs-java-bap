package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientItem {
    private String itemId;
    private String itemCode;
    private String itemName;
    private String minPrice;
    private String maxPrice;
    private String startTime;
    private String endTime;
    private Map<String, String> tags;
}
