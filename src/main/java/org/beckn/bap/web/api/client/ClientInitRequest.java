package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInitRequest {
     private String domain;
     private String transactionId;
     private List<ClientSelectItemRequest> items;
     private ClientBilling billing;
     private ClientFulfilment fulfilment;

}
