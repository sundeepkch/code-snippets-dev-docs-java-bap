package org.beckn.bap.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.client.ClientHttpResponse;

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
