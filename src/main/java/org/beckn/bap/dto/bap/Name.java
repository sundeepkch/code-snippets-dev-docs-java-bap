package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Name {
    @JsonFormat(pattern = "^./[^/]+/[^/]*/[^/]*/[^/]*/[^/]*/[^/]*$")
    private String name;
}
