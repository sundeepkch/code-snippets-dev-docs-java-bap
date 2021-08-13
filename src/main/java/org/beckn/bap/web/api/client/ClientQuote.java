package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientQuote {
    private String currency;
    private String price;
    private List<ClientQuoteBreakup> breakup;
}
