package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Scalar {
    private TypeEnum type;
    private BigDecimal value;
    @JsonProperty("estimated_value")
    private BigDecimal estimatedValue;
    @JsonProperty("computed_value")
    private BigDecimal computedValue;
    private ScalarRange range;
    private String unit;

    public enum TypeEnum {
        CONSTANT("CONSTANT"),
        VARIABLE("VARIABLE");

        static Map<String, TypeEnum> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(TypeEnum::getValue, Function.identity()));

        String value;

        TypeEnum(String value) {
            this.value = value;
        }

        public static TypeEnum lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }

    }
}
