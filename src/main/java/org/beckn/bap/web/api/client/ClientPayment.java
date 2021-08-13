package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPayment {
    private String uri;
    private String method;
    private String amount;
    private String mode;
    private String vpa ;
    private String type;
    private String status;

}
