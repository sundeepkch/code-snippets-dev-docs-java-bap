package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentParams {
    private String transaction_id;
    private String transaction_status;
    private DecimalValue amount;
    private String currency;
    private PaymentTypeEnum type;
    private PaymentStatusEnum status;
    private Time time;


    public enum PaymentTypeEnum {
        ON_ORDER("ON-ORDER"),
        PRE_FULFILLMENT("PRE-FULFILLMENT"),
        ON_FULFILLMENT("ON-FULFILLMENT"),
        POST_FULFILLMENT("POST-FULFILLMENT");

        static Map<String, PaymentTypeEnum> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(PaymentTypeEnum::getValue, Function.identity()));

        String value;

        PaymentTypeEnum(String value) {
            this.value = value;
        }

        public static PaymentTypeEnum lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }
    }

    public enum PaymentStatusEnum {
        PAID("PAID"),
        NOT_PAID("NOT-PAID"),
        ON_FULFILLMENT("ON-FULFILLMENT"),
        POST_FULFILLMENT("POST-FULFILLMENT");

        static Map<String, PaymentStatusEnum> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(PaymentStatusEnum::getValue, Function.identity()));

        String value;

        PaymentStatusEnum(String value) {
            this.value = value;
        }

        public static PaymentStatusEnum lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }
    }

}
