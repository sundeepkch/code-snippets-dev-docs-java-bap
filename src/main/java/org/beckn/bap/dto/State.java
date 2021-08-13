package org.beckn.bap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class State {
    private Descriptor descriptor;
    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
    @JsonProperty("updated_by")
    private String updatedBy;
}
