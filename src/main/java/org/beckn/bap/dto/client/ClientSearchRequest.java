package org.beckn.bap.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearchRequest {
    private String transactionId;
    private String startLocation;
    private String endLocation;
    private String type;
    private String itemId;
    private String itemCode;
    private String itemName;
    private String minPrice;
    private String maxPrice;
    private String sellerId;
    private String sellerName;
    private BigDecimal rating;

    @JsonIgnore
    private String domain;
}
