package com.histudio.base.entity;

import com.histudio.base.IItem;

/**
 * 个人信息
 *
 * @author ljh
 */
public class User implements IItem {

    /**
     * 全局唯一标识
     */
    private String uuid;
    /**
     * ID : WSQ
     * NAME : 王世清
     * NAME_PY : WSQ
     * LOCATION : 0
     * STOCK : 002
     * DEPARTMENT : 005
     * SEX : 0
     * BIRTHDAY : 2017-12-29 16:03:14
     * E_MAIL : null
     * OFFICE_PHONE : null
     * PHONE : null
     * MOBILE : 13799961626
     * HOME_PHONE : null
     * BANK_CARD : null
     * BANK : null
     * IS_OPERATOR : 1
     * IS_SALE_MAN : 1
     * IS_CLEANER : 1
     * IS_REPAIRER : 1
     * IS_DELIVER : 1
     * NEED_INFORMAT : 1
     * APP_ALLOW_ACCESS : 1
     * APP_LOGIN_ID : 31734378
     * APP_ACCESS_PASSWORD : 5696232
     * APP_ALLOW_CALL_BACK : 1
     * KEY_VALUE : 87208100
     * SHOW_CTI_QUEUE : 1
     * SHOW_IMPORTANT_MESSAGE : 1
     * DEFAULT_EXTENSION : 999
     * SIGN_QUEUE : 8000
     * ENABLED : 1
     * LOGON : 1
     * LOGON_PHONE : null
     * LOGON_IP_ADDRESS : null
     * LOGIN_ON_TIME : 2018-09-23 13:23:37
     * LOGIN_OFF_TIME : 2018-09-18 11:24:51
     * DND : 0
     * DND_ON_TIME : null
     * DND_OFF_TIME : null
     * LATITUDE : 26.02583200
     * LONGITUDE : 119.25990700
     * LOCAL_GPS_TIME : 2018-09-10 12:03:42
     * LAST_INFORMAT_TIME : 2017-12-29 16:03:14
     * NOT_RECEIVED_ORDERS : 0
     * LAST_REFRESH_ORDERS_TIME : 2017-12-29 16:03:14
     * IS_COMPLAINER : 1
     * SIGNED : 1
     * SIGNED_PHONE : null
     * SIGNED_IP_ADDRESS : null
     * SIGNED_ON_TIME : 2018-03-21 10:45:17
     * SIGNED_OFF_TIME : null
     * IS_ESCORTER : 1
     * ONLINE_MESSAGE_SERVICE : 0
     * IS_LEADER : 1
     * ALLOW_PC_TERMINAL : 1
     * ALLOW_APP_TERMINAL : 1
     * STOCK_LEADER : 0
     * IS_DRIVER : 1
     * IS_STOCKER : 0
     * BOTTLE_STORAGE_SEND_SMS : 0
     * LOCK_EXTENSION : 0
     * IS_DELIVER_MOVED : 0
     * SALE_MAN_UNRETURN_AMOUNT : 0.00
     */

    private String ID;
    private String NAME;
    private String NAME_PY;
    private String LOCATION;
    private String STOCK;
    private String STOCK_NAME;
    private String DEPARTMENT;
    private String SEX;
    private String BIRTHDAY;
    private Object E_MAIL;
    private Object OFFICE_PHONE;
    private Object PHONE;
    private String MOBILE;
    private Object HOME_PHONE;
    private Object BANK_CARD;
    private Object BANK;
    private String IS_OPERATOR;
    private String IS_SALE_MAN;
    private String IS_CLEANER;
    private String IS_REPAIRER;
    private String IS_DELIVER;
    private String NEED_INFORMAT;
    private String APP_ALLOW_ACCESS;
    private int APP_LOGIN_ID;
    private String APP_ACCESS_PASSWORD;
    private String APP_ALLOW_CALL_BACK;
    private String KEY_VALUE;
    private String SHOW_CTI_QUEUE;
    private String SHOW_IMPORTANT_MESSAGE;
    private String DEFAULT_EXTENSION;
    private String SIGN_QUEUE;
    private String ENABLED;
    private String LOGON;
    private Object LOGON_PHONE;
    private Object LOGON_IP_ADDRESS;
    private String LOGIN_ON_TIME;
    private String LOGIN_OFF_TIME;
    private String DND;
    private Object DND_ON_TIME;
    private Object DND_OFF_TIME;
    private String LATITUDE;
    private String LONGITUDE;
    private String LOCAL_GPS_TIME;
    private String LAST_INFORMAT_TIME;
    private String NOT_RECEIVED_ORDERS;
    private String LAST_REFRESH_ORDERS_TIME;
    private String IS_COMPLAINER;
    private String SIGNED;
    private Object SIGNED_PHONE;
    private Object SIGNED_IP_ADDRESS;
    private String SIGNED_ON_TIME;
    private Object SIGNED_OFF_TIME;
    private String IS_ESCORTER;
    private String ONLINE_MESSAGE_SERVICE;
    private String IS_LEADER;
    private String ALLOW_PC_TERMINAL;
    private String ALLOW_APP_TERMINAL;
    private String STOCK_LEADER;
    private String IS_DRIVER;
    private String IS_STOCKER;
    private String BOTTLE_STORAGE_SEND_SMS;
    private String LOCK_EXTENSION;
    private String IS_DELIVER_MOVED;
    private String SALE_MAN_UNRETURN_AMOUNT;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME_PY() {
        return NAME_PY;
    }

    public void setNAME_PY(String NAME_PY) {
        this.NAME_PY = NAME_PY;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getSTOCK_NAME() {
        return STOCK_NAME;
    }

    public void setSTOCK_NAME(String STOCK_NAME) {
        this.STOCK_NAME = STOCK_NAME;
    }

    public String getSTOCK() {
        return STOCK;
    }

    public void setSTOCK(String STOCK) {
        this.STOCK = STOCK;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(String BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    public Object getE_MAIL() {
        return E_MAIL;
    }

    public void setE_MAIL(Object E_MAIL) {
        this.E_MAIL = E_MAIL;
    }

    public Object getOFFICE_PHONE() {
        return OFFICE_PHONE;
    }

    public void setOFFICE_PHONE(Object OFFICE_PHONE) {
        this.OFFICE_PHONE = OFFICE_PHONE;
    }

    public Object getPHONE() {
        return PHONE;
    }

    public void setPHONE(Object PHONE) {
        this.PHONE = PHONE;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public Object getHOME_PHONE() {
        return HOME_PHONE;
    }

    public void setHOME_PHONE(Object HOME_PHONE) {
        this.HOME_PHONE = HOME_PHONE;
    }

    public Object getBANK_CARD() {
        return BANK_CARD;
    }

    public void setBANK_CARD(Object BANK_CARD) {
        this.BANK_CARD = BANK_CARD;
    }

    public Object getBANK() {
        return BANK;
    }

    public void setBANK(Object BANK) {
        this.BANK = BANK;
    }

    public String getIS_OPERATOR() {
        return IS_OPERATOR;
    }

    public void setIS_OPERATOR(String IS_OPERATOR) {
        this.IS_OPERATOR = IS_OPERATOR;
    }

    public String getIS_SALE_MAN() {
        return IS_SALE_MAN;
    }

    public void setIS_SALE_MAN(String IS_SALE_MAN) {
        this.IS_SALE_MAN = IS_SALE_MAN;
    }

    public String getIS_CLEANER() {
        return IS_CLEANER;
    }

    public void setIS_CLEANER(String IS_CLEANER) {
        this.IS_CLEANER = IS_CLEANER;
    }

    public String getIS_REPAIRER() {
        return IS_REPAIRER;
    }

    public void setIS_REPAIRER(String IS_REPAIRER) {
        this.IS_REPAIRER = IS_REPAIRER;
    }

    public String getIS_DELIVER() {
        return IS_DELIVER;
    }

    public void setIS_DELIVER(String IS_DELIVER) {
        this.IS_DELIVER = IS_DELIVER;
    }

    public String getNEED_INFORMAT() {
        return NEED_INFORMAT;
    }

    public void setNEED_INFORMAT(String NEED_INFORMAT) {
        this.NEED_INFORMAT = NEED_INFORMAT;
    }

    public String getAPP_ALLOW_ACCESS() {
        return APP_ALLOW_ACCESS;
    }

    public void setAPP_ALLOW_ACCESS(String APP_ALLOW_ACCESS) {
        this.APP_ALLOW_ACCESS = APP_ALLOW_ACCESS;
    }

    public int getAPP_LOGIN_ID() {
        return APP_LOGIN_ID;
    }

    public void setAPP_LOGIN_ID(int APP_LOGIN_ID) {
        this.APP_LOGIN_ID = APP_LOGIN_ID;
    }

    public String getAPP_ACCESS_PASSWORD() {
        return APP_ACCESS_PASSWORD;
    }

    public void setAPP_ACCESS_PASSWORD(String APP_ACCESS_PASSWORD) {
        this.APP_ACCESS_PASSWORD = APP_ACCESS_PASSWORD;
    }

    public String getAPP_ALLOW_CALL_BACK() {
        return APP_ALLOW_CALL_BACK;
    }

    public void setAPP_ALLOW_CALL_BACK(String APP_ALLOW_CALL_BACK) {
        this.APP_ALLOW_CALL_BACK = APP_ALLOW_CALL_BACK;
    }

    public String getKEY_VALUE() {
        return KEY_VALUE;
    }

    public void setKEY_VALUE(String KEY_VALUE) {
        this.KEY_VALUE = KEY_VALUE;
    }

    public String getSHOW_CTI_QUEUE() {
        return SHOW_CTI_QUEUE;
    }

    public void setSHOW_CTI_QUEUE(String SHOW_CTI_QUEUE) {
        this.SHOW_CTI_QUEUE = SHOW_CTI_QUEUE;
    }

    public String getSHOW_IMPORTANT_MESSAGE() {
        return SHOW_IMPORTANT_MESSAGE;
    }

    public void setSHOW_IMPORTANT_MESSAGE(String SHOW_IMPORTANT_MESSAGE) {
        this.SHOW_IMPORTANT_MESSAGE = SHOW_IMPORTANT_MESSAGE;
    }

    public String getDEFAULT_EXTENSION() {
        return DEFAULT_EXTENSION;
    }

    public void setDEFAULT_EXTENSION(String DEFAULT_EXTENSION) {
        this.DEFAULT_EXTENSION = DEFAULT_EXTENSION;
    }

    public String getSIGN_QUEUE() {
        return SIGN_QUEUE;
    }

    public void setSIGN_QUEUE(String SIGN_QUEUE) {
        this.SIGN_QUEUE = SIGN_QUEUE;
    }

    public String getENABLED() {
        return ENABLED;
    }

    public void setENABLED(String ENABLED) {
        this.ENABLED = ENABLED;
    }

    public String getLOGON() {
        return LOGON;
    }

    public void setLOGON(String LOGON) {
        this.LOGON = LOGON;
    }

    public Object getLOGON_PHONE() {
        return LOGON_PHONE;
    }

    public void setLOGON_PHONE(Object LOGON_PHONE) {
        this.LOGON_PHONE = LOGON_PHONE;
    }

    public Object getLOGON_IP_ADDRESS() {
        return LOGON_IP_ADDRESS;
    }

    public void setLOGON_IP_ADDRESS(Object LOGON_IP_ADDRESS) {
        this.LOGON_IP_ADDRESS = LOGON_IP_ADDRESS;
    }

    public String getLOGIN_ON_TIME() {
        return LOGIN_ON_TIME;
    }

    public void setLOGIN_ON_TIME(String LOGIN_ON_TIME) {
        this.LOGIN_ON_TIME = LOGIN_ON_TIME;
    }

    public String getLOGIN_OFF_TIME() {
        return LOGIN_OFF_TIME;
    }

    public void setLOGIN_OFF_TIME(String LOGIN_OFF_TIME) {
        this.LOGIN_OFF_TIME = LOGIN_OFF_TIME;
    }

    public String getDND() {
        return DND;
    }

    public void setDND(String DND) {
        this.DND = DND;
    }

    public Object getDND_ON_TIME() {
        return DND_ON_TIME;
    }

    public void setDND_ON_TIME(Object DND_ON_TIME) {
        this.DND_ON_TIME = DND_ON_TIME;
    }

    public Object getDND_OFF_TIME() {
        return DND_OFF_TIME;
    }

    public void setDND_OFF_TIME(Object DND_OFF_TIME) {
        this.DND_OFF_TIME = DND_OFF_TIME;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getLOCAL_GPS_TIME() {
        return LOCAL_GPS_TIME;
    }

    public void setLOCAL_GPS_TIME(String LOCAL_GPS_TIME) {
        this.LOCAL_GPS_TIME = LOCAL_GPS_TIME;
    }

    public String getLAST_INFORMAT_TIME() {
        return LAST_INFORMAT_TIME;
    }

    public void setLAST_INFORMAT_TIME(String LAST_INFORMAT_TIME) {
        this.LAST_INFORMAT_TIME = LAST_INFORMAT_TIME;
    }

    public String getNOT_RECEIVED_ORDERS() {
        return NOT_RECEIVED_ORDERS;
    }

    public void setNOT_RECEIVED_ORDERS(String NOT_RECEIVED_ORDERS) {
        this.NOT_RECEIVED_ORDERS = NOT_RECEIVED_ORDERS;
    }

    public String getLAST_REFRESH_ORDERS_TIME() {
        return LAST_REFRESH_ORDERS_TIME;
    }

    public void setLAST_REFRESH_ORDERS_TIME(String LAST_REFRESH_ORDERS_TIME) {
        this.LAST_REFRESH_ORDERS_TIME = LAST_REFRESH_ORDERS_TIME;
    }

    public String getIS_COMPLAINER() {
        return IS_COMPLAINER;
    }

    public void setIS_COMPLAINER(String IS_COMPLAINER) {
        this.IS_COMPLAINER = IS_COMPLAINER;
    }

    public String getSIGNED() {
        return SIGNED;
    }

    public void setSIGNED(String SIGNED) {
        this.SIGNED = SIGNED;
    }

    public Object getSIGNED_PHONE() {
        return SIGNED_PHONE;
    }

    public void setSIGNED_PHONE(Object SIGNED_PHONE) {
        this.SIGNED_PHONE = SIGNED_PHONE;
    }

    public Object getSIGNED_IP_ADDRESS() {
        return SIGNED_IP_ADDRESS;
    }

    public void setSIGNED_IP_ADDRESS(Object SIGNED_IP_ADDRESS) {
        this.SIGNED_IP_ADDRESS = SIGNED_IP_ADDRESS;
    }

    public String getSIGNED_ON_TIME() {
        return SIGNED_ON_TIME;
    }

    public void setSIGNED_ON_TIME(String SIGNED_ON_TIME) {
        this.SIGNED_ON_TIME = SIGNED_ON_TIME;
    }

    public Object getSIGNED_OFF_TIME() {
        return SIGNED_OFF_TIME;
    }

    public void setSIGNED_OFF_TIME(Object SIGNED_OFF_TIME) {
        this.SIGNED_OFF_TIME = SIGNED_OFF_TIME;
    }

    public String getIS_ESCORTER() {
        return IS_ESCORTER;
    }

    public void setIS_ESCORTER(String IS_ESCORTER) {
        this.IS_ESCORTER = IS_ESCORTER;
    }

    public String getONLINE_MESSAGE_SERVICE() {
        return ONLINE_MESSAGE_SERVICE;
    }

    public void setONLINE_MESSAGE_SERVICE(String ONLINE_MESSAGE_SERVICE) {
        this.ONLINE_MESSAGE_SERVICE = ONLINE_MESSAGE_SERVICE;
    }

    public String getIS_LEADER() {
        return IS_LEADER;
    }

    public void setIS_LEADER(String IS_LEADER) {
        this.IS_LEADER = IS_LEADER;
    }

    public String getALLOW_PC_TERMINAL() {
        return ALLOW_PC_TERMINAL;
    }

    public void setALLOW_PC_TERMINAL(String ALLOW_PC_TERMINAL) {
        this.ALLOW_PC_TERMINAL = ALLOW_PC_TERMINAL;
    }

    public String getALLOW_APP_TERMINAL() {
        return ALLOW_APP_TERMINAL;
    }

    public void setALLOW_APP_TERMINAL(String ALLOW_APP_TERMINAL) {
        this.ALLOW_APP_TERMINAL = ALLOW_APP_TERMINAL;
    }

    public String getSTOCK_LEADER() {
        return STOCK_LEADER;
    }

    public void setSTOCK_LEADER(String STOCK_LEADER) {
        this.STOCK_LEADER = STOCK_LEADER;
    }

    public String getIS_DRIVER() {
        return IS_DRIVER;
    }

    public void setIS_DRIVER(String IS_DRIVER) {
        this.IS_DRIVER = IS_DRIVER;
    }

    public String getIS_STOCKER() {
        return IS_STOCKER;
    }

    public void setIS_STOCKER(String IS_STOCKER) {
        this.IS_STOCKER = IS_STOCKER;
    }

    public String getBOTTLE_STORAGE_SEND_SMS() {
        return BOTTLE_STORAGE_SEND_SMS;
    }

    public void setBOTTLE_STORAGE_SEND_SMS(String BOTTLE_STORAGE_SEND_SMS) {
        this.BOTTLE_STORAGE_SEND_SMS = BOTTLE_STORAGE_SEND_SMS;
    }

    public String getLOCK_EXTENSION() {
        return LOCK_EXTENSION;
    }

    public void setLOCK_EXTENSION(String LOCK_EXTENSION) {
        this.LOCK_EXTENSION = LOCK_EXTENSION;
    }

    public String getIS_DELIVER_MOVED() {
        return IS_DELIVER_MOVED;
    }

    public void setIS_DELIVER_MOVED(String IS_DELIVER_MOVED) {
        this.IS_DELIVER_MOVED = IS_DELIVER_MOVED;
    }

    public String getSALE_MAN_UNRETURN_AMOUNT() {
        return SALE_MAN_UNRETURN_AMOUNT;
    }

    public void setSALE_MAN_UNRETURN_AMOUNT(String SALE_MAN_UNRETURN_AMOUNT) {
        this.SALE_MAN_UNRETURN_AMOUNT = SALE_MAN_UNRETURN_AMOUNT;
    }
    /**
     * code : 83745619
     * im_account : 05a071fd949a11d58764bd3de94b6f16o9by9ltu
     * im_password : 0pUfFNmaKJq6xuNXIkPs5ZgFjVc26aGwCwPM
     * person_info_data : {"birthday":"","birthday_name":"null","head_portrait_url":"http://dev-images.thedeer.cn/user/userdefaultheadportrait.jpg-thumbnail_240","BOTTLE_NAME":"null","name_english":"null","name_spell":"null","nick_name":"138****2494","sex":"0"}
     * platform_data : {"is_email_login":"0","is_phone_login":"1","is_qq_login":"0","is_weibo_login":"0","is_weixin_login":"0"}
     * setting_data : {"is_push":"1"}
     * user_id : 3
     */


}
