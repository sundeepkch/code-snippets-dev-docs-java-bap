package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRatingRequest {
    private String domain;
    private String transactionId;
    private String orderId;
    private Integer rating;
}
