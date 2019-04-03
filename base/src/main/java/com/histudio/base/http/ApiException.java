package com.histudio.base.http;

/**
 * Created by ljh on 16/3/10.
 */
public class ApiException extends RuntimeException {

    public static final int ERROR_10105 = 10105;
    public static final int ERROR_20001 = 20001;
    public static final int ERROR_20002 = 20002;
    public static final int ERROR_20003 = 20003;
    public static final int ERROR_20004 = 20004;
    public static final int ERROR_20005 = 20005;
    private int resultCode;
    private String msg;

    public ApiException(int resultCode, String msg) {
        this(getApiExceptionMessage(resultCode, msg));
        this.resultCode =resultCode;
        this.msg = msg;
    }

    public ApiException(String detailMessage) {
        super(detailMessage);

    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     */
    private static String getApiExceptionMessage(int code, String msg) {

        String message = "";
        switch (code) {
//            case SESSION_TIME_OUT:
//                message = "session过期";
//                break;
//            case SESSION_START_FAIL:
//                message = "session不存在或已过期";
//                break;
//            case CLIENT_FAULT:
//                message = "客户端数据错误";
//                break;
//            case SERVER_FAULT:
//                message = "服务端数据错误";
//                break;
//            case PHONE_REGISTERED:
//                message = "手机号已经被注册过";
//                break;
//            case RECOMMEND_IS_MISSING:
//                message = "推荐人不存在";
//                break;
//            case PHONE_IS_NOT_REGISTER:
//                message = "手机未注册";
//                break;
//            case PASSWORD_WRONG:
//                message = "密码不正确";
//                break;
//            case USER_IS_NOT_EXIT:
//                message = "用户不存在";
//                break;
//            case PHONE_CODE_WRONG:
//                message = "验证码错误";
//                break;

            default:
                message = msg;

        }
        return message;
    }
}

