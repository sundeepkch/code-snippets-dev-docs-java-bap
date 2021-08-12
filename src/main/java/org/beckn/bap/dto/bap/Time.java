package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Time {
    private String label = null;
    private String timestamp = null;
    private String duration = null;
    private TimeRange range = null;
    private String days = null;
}
