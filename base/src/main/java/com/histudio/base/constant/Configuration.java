package com.histudio.base.constant;

/**
 * 配置文件，发布和调试的时候做修改
 *
 * @author ljh
 */
public interface Configuration {

    // 调试模式会输出日志等信息
    boolean IS_DEBUG = true;
    // 是不是开发者模式
    boolean IS_DEV_MODE = true;

    // 正式环境
    String BaseProHost = "http://gas.devstudio.cn/";
    //测试环境
    String BaseDevHost = "http://dev-gas.devstudio.cn/";

    int[] APP_SECRET = {50, 55, 100, 102, 50, 98, 49, 56, 45, 49, 102, 52, 56, 45, 50, 99, 99, 98, 45, 99, 57, 100, 52, 45, 48, 51, 100, 56, 101, 101, 53, 54, 51, 52, 55, 48};

}