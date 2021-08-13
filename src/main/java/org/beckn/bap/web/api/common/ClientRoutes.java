package org.beckn.bap.web.api.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientRoutes {

    private static final String BASE_URI = "/client";
    private static final String DOMAIN_MOBILITY_URI = BASE_URI + "/mobility";
    private static final String DOMAIN_RETAIL_URI = BASE_URI + "/local_retail";
    //Local Retails Endpoints
    public static final String PARAM_MESSAGE_ID = "messageId";
    public static final String SEARCH_API = DOMAIN_RETAIL_URI + "/search";
    public static final String SEARCH_BY_MSG_ID_API = SEARCH_API + "/{messageId}";


    //Mobility Endpoints
    public static final String SEARCH_BY_PICKUP_DROP_API = DOMAIN_MOBILITY_URI + "/search_by_pickup_drop_location";

}
