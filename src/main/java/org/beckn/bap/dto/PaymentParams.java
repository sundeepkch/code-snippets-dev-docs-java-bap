package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.toMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentParams {
    private String transaction_id;
    private String transaction_status;
    private String amount;
    private String mode;
    private String vpa;
    private String currency;

    private Time time;



}
