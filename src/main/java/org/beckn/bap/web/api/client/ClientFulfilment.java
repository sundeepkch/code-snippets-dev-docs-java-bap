package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientFulfilment {
    private String type;
    private String startLocationId;
    private String startLocationName;
    private String startLocationGps;
    private String startInstructionName;
    private String startInstructionDesc;
    private String endLocationId;
    private String endLocationName;
    private String endLocationGps;
    private String endInstructionName;
    private String endInstructionDesc;
    // rest of the fields
}
