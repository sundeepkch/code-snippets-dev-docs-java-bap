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
public class Tracking {
    private String url;
    private TrackingStatus status;

    public enum TrackingStatus {
        active("active"),
        inactive("inactive");

        static Map<String, TrackingStatus> VALUE_MAP = Arrays.stream(values())
                .collect(toMap(TrackingStatus::getValue, Function.identity()));

        String value;

        TrackingStatus(String value) {
            this.value = value;
        }

        public static TrackingStatus lookup(String value) {
            return VALUE_MAP.getOrDefault(value, null);
        }

        public String getValue() {
            return value;
        }

    }
}
