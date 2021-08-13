package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSupportRequest {
    private String domain;
    private String transactionId;
    private String refId;
}
