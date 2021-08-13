package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearchRequest {
    private String domain;
    private String transactionId;
    private ClientFulfilment fulfilment;
    private ClientItem item;
    private String sellerId;
    private String sellerName;
    private BigDecimal rating;
}
