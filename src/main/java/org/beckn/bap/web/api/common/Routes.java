package org.beckn.bap.web.api.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Routes {
    private static final String BASE_URI = "/bap";
    public static final String ON_SEARCH_API = BASE_URI + "/on_search";
    public static final String ON_SELECT_API = BASE_URI + "/on_select";
    public static final String ON_INIT_API = BASE_URI + "/on_init";
    public static final String ON_CONFIRM_API = BASE_URI + "/on_confirm";
    public static final String ON_STATUS_API = BASE_URI + "/on_status";
    public static final String ON_TRACK_API = BASE_URI + "/on_track";
    public static final String ON_CANCEL_API = BASE_URI + "/on_cancel";
    public static final String ON_UPDATE_API = BASE_URI + "/on_update";
    public static final String ON_RATING_API = BASE_URI + "/on_rating";
    public static final String ON_SUPPORT_API = BASE_URI + "/on_support";
}
