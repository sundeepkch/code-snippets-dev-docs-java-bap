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
public class Error {
    private ErrorTypeEnum type;
    private String code;
    private String path;
    private String message;

    public enum ErrorTypeEnum {
        CONTEXT_ERROR("CONTEXT-ERROR"),
        CORE_ERROR("CORE-ERROR"),
        DOMAIN_ERROR("DOMAIN-ERROR"),
        POLICY_ERROR("POLICY-ERROR"),
        JSON_SCHEMA_ERROR("JSON-SCHEMA-ERROR");

        static Map<String, ErrorTypeEnum> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(ErrorTypeEnum::getValue, Function.identity()));

        String value;

        ErrorTypeEnum(String value) {
            this.value = value;
        }

        public static ErrorTypeEnum lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }

    }
}
