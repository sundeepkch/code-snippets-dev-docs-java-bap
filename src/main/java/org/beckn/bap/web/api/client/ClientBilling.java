package org.beckn.bap.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBilling {
    private String name;
    private String email;
    private String phone;
    private String addressDoor;
    private String addressName;
    private String addressLocality;
    private String addressCity;
    private String addressState;
    private String addressCountry;
    private String addressAreaCode;
    private ClientOrganization organization;
}
