package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateOrderRequest {
    private String domain;
    private String transactionId;
    private String orderId;
    private String orderState;
    private List<ClientSelectItemRequest> items;
    private ClientBilling billing;
    private ClientFulfilment fulfilment;
    private ClientQuote quote;
    private ClientPayment payment;
}
