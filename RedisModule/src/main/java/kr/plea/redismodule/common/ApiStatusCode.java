package kr.plea.redismodule.common;

public interface ApiStatusCode {
    /**
     * 공통
     */
    String OK = "200";
    String BAD_REQUEST = "400";
    String UNAUTHORIZED = "401";
    String FORBIDDEN = "403";
    String NOT_FOUND = "404";
    String SERVER_ERROR = "500";
    String SERVICE_UNAVAILABLE = "503";

    /**
     * 받아보기
     */
    String NCAS_AUTHORIZED = "1000";
    String DUPLICATE_PHONE_SUBSCRIBE = "1001";
    String UNAUTHORIZED_REQUEST = "1002";
    String SEND_SMS_FAILURE = "1003";
    String INVALID_AUTHORIZED_CODE = "1004";
    String EXCEEDED_CODE = "1005";
    String TEXT_AUTHORIZED_EXPIRED = "1006";
    String NOT_EXIST_REQUEST_NUMBER = "1007";
    String UNABLE_SEARCH_CONTENT = "1008";
    String NOT_REQUIRED_AGREE = "1009";
    String UNAUTHORIZED_SESSION = "1010";
    String NO_CATEGORY_SELECTED = "1011";
    String UNDER_AGE = "1012";
    String NOT_EXIST_SUBSCRIBER = "1014";
    String EXCEEDED_AUTH = "1015";
    String AUTHORIZED_CORPORATION = "1017";
    String TABOOLA_ERROR = "51001005";
    String DUPLICATE_SUBSCRIPTION_REQUESTS = "1020";
    String KCB_BAD_REQUEST = "1100";
    String KCB_VERIFICATION_FAIL = "1101";
    String KCB_SESSION_TIMEOUT = "1102";
    String KCB_AUTH_CANCEL = "1103";
    String KCB_ERROR = "53001006";

}
