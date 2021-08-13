package org.beckn.bap.web.api.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientRoutes {

    private static final String BASE_URI = "/client";
    //Client Endpoints
    public static final String PARAM_MESSAGE_ID = "messageId";
    public static final String SEARCH_API = BASE_URI + "/search";
    public static final String SEARCH_BY_MSG_ID_API = SEARCH_API + "/{messageId}";
    public static final String SELECT_API = BASE_URI + "/select";
    public static final String INIT_ORDER_API = BASE_URI + "/init";
    public static final String CONFIRM_ORDER_API = BASE_URI + "/confirm";
    public static final String ORDER_STATUS_API = BASE_URI + "/status";
    public static final String TRACK_ORDER_API = BASE_URI + "/track";
    public static final String CANCEL_ORDER_API = BASE_URI + "/cancel";
    public static final String UPDATE_ORDER_API = BASE_URI + "/update";
    public static final String RATE_ORDER_API = BASE_URI + "/rate";
    public static final String SUPPORT_API = BASE_URI + "/support";



}
