package com.histudio.base.constant;

/**
 * 常量
 *
 * @author qsj
 */
public interface BConstants {


    // =======================消息处理10000 开始===========================
    int ALERT_CLOCK_1_MIN = 10001;
    int ALERT_CLOCK_5_MIN = 10002;
    int ALERT_CLOCK_15_MIN = 10003;
    int ALERT_CLOCK_60_MIN = 10004;
    // =======================消息处理5000 开始===========================


    String DEFALUT_CODESET = "UTF-8";

    String ALARM_REGISTER_1MIN_CLOCK = "com.histudio.yuntu.alarm_register_1min_clock";
    String ALARM_REGISTER_5MIN_CLOCK = "com.histudio.yuntu.alarm_register_5min_clock";
    String ALARM_REGISTER_15MIN_CLOCK = "com.histudio.yuntu.alarm_register_15min_clock";
    String ALARM_REGISTER_60MIN_CLOCK = "com.histudio.yuntu.alarm_register_60min_clock";
    // --------------------------------定时---------------------------
    // 间隔时间5分
    long TIME_DELAY_REL_MIN = 1000 * 60 * 1L;
    long TIME_DELAY_REL_SHORT = 1000 * 60 * 5L;
    // 间隔时间15分
    long TIME_DELAY_REL_NORMAL = 1000 * 60 * 15L;
    // 间隔时间1小时
    long TIME_DELAY_REL_LONG = 1000 * 60 * 60L;

    // 客户端类型
    int CLIENT_TYPE_ANDROID_PHO = 1000;

    // ===========================渠道管理===具体渠道见渠道.txt=============
    String CHANNEL_KEY = "UMENG_CHANNEL";

    // =======================消息处理5000 开始===========================
    int INIT_APP_IN_BACKGROUD_SUCC = 5000;
    int INIT_APP_IN_BACKGROUD_FAIL = 5001;
    int LOGOUT_SET_VIEW = 5003;
    int UPDATE_ORDER = 5015;
    int GOTO_FRAM_BY_MARK = 5005;
    int ORDER_REFRESH = 5030;
    int CUSTOM_REFRESH = 5031;
    int SHOW_LOADING_VIEW = 5034;
    int HIDE_LOADING_VIEW = 5035;
    int TASK_LOADED = 5036;
    int TASK_LOADING = 5037;
    int TASK_LOADFAIL = 5038;
    int ANJIAN_SUC = 5039;
    int SHOW_LOADING_DIALOG = 5040;
    int HIDE_LOADING_DIALOG = 5041;
    int UPDATE_SUC = 5042;

    // =======================消息处理5000 结束===========================

    int COMMON_PAGE_SIZE = 10;
    int FRAME_MARK_LOADING_FRAME = 1109;
    int FRAME_MARK_LOGIN_FRAME = 1110;
    int FRAME_MARK_MAIN_FRAME = 1111;
    int FRAME_MARK_GROUP_FRAME = 1112;

    String IS_GUID_VERSION = "guid_version_2";

    String LANGUAGE = "language";
    String USER_INFO = "userinfo";
    String ANJIAN_INFO = "anjianinfo";
    String USER_NAME = "username";
    String ORDER_INFO = "order_info";
    String ORDER_NO = "order_no";
    String CUSTOM_NO = "custom_no";
    String GANGPING_INFO = "gangping_info";

    // -----------------------------推送计算------------------------------

}
