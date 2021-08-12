package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Payment {
    private String uri;
    @JsonProperty("tl_method")
    private TlMethod tlMethod;
    private PaymentParams params;

    public enum TlMethod {
        http_get("http/get"),
        http_post("http/post");

        static Map<String, TlMethod> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(TlMethod::getValue, Function.identity()));

        String value;

        TlMethod(String value) {
            this.value = value;
        }

        public static TlMethod lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }

    }
}
