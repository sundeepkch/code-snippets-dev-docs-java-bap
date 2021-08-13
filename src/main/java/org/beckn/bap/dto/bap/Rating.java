package org.beckn.bap.dto.bap;

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
public class Rating {
    private BigDecimal value;
    private String unit = "U+2B50";
    @JsonProperty("max_value")
    private BigDecimal maxValue = new BigDecimal(5);

    private DirectionEnum direction;

    public String getDirection() {
        return direction.getValue();
    }

    public enum DirectionEnum {
        UP("UP"),
        DOWN("DOWN");

        static Map<String, DirectionEnum> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(DirectionEnum::getValue, Function.identity()));

        String value;

        DirectionEnum(String value) {
            this.value = value;
        }

        public static DirectionEnum lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }

    }
}
