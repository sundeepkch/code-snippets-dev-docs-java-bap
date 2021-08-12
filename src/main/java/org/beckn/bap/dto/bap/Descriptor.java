package org.beckn.bap.dto.bap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Descriptor {
    private String name;
    private String code;
    private String symbol;
    @JsonProperty("short_desc")
    private String shortDesc;
    @JsonProperty("long_desc")
    private String longDesc;
    private List<Image> images;
    private String audio;
    @JsonProperty("3d_render")
    private String _3dRender;
}
