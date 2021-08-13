package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private String messageId;
    private String transactionId;

    public static ClientResponse of(String messageId, String transactionId) {
        return new ClientResponse(messageId, transactionId);
    }
}
