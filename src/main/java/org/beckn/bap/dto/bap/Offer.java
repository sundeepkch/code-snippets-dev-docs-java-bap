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
public class Offer {
    private String id;
    private Descriptor descriptor;
    @JsonProperty("location_ids")
    private List<String> locationIds;
    @JsonProperty("category_ids")
    private List<String> category_Ids;
    @JsonProperty("item_ids")
    private List<String> itemIds;
    private Time time;

}
