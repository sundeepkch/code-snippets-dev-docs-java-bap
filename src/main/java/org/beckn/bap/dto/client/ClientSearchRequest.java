package org.beckn.bap.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearchRequest {
    private String startLocation;
    private String endLocation;
    private String domain;
}
