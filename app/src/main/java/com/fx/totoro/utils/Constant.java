package com.fx.totoro.utils;

/**
 * Created by chenshaodong on 2018/6/3.
 */

public class Constant {

    /*文件名*/
    public static final String XSP_FILE = "totoro";

    /*包名*/
    public static final String PKG_NAME = "com.fx.totoro";

    /*系统硬件静态信息*/
    public static final String[] BUILD_ARR = {"TAGS", "HOST", "USER", "TIME", "DISPLAY", "BOOTLOADER", "SERIAL", "BOARD",
            "BRAND", "DEVICE", "FINGERPRINT", "HARDWARE", "MANUFACTURER", "TYPE", "MODEL", "PRODUCT", "ID"};

    /*Build类method*/
    public static final String[] BUILD_METHOD = {"getRadioVersion", "getSerial"};

    /*版本相关信息*/
    public static final String[] VERSION_ARR = {"RELEASE", "INCREMENTAL", "CODENAME", "SDK_INT", "SDK"};

    /*电话相关信息*/
    public static final String[] TEL_ARR = {"getDeviceId", "getNetworkOperator", "getNetworkOperatorName", "getNetworkType", "getSimOperator", "getSimSerialNumber",
            "getSimOperatorName", "getSubscriberId", "getDataActivity", "getDeviceSoftwareVersion", "getLine1Number", "getSimState", "getPhoneType", "hasIccCard"};

    /*网络相关信息*/
    public static final String[] NETWORK_ARR = {"getExtraInfo", "getReason", "getSubtype", "getSubtypeName", "getType", "getTypeName"};

    /*本机ip和主机名信息*/
    public static final String[] INET_ARR = {"getHostName", "getCanonicalHostName", "getAddress", "getHostAddress"};

    /*wifi相关信息*/
    public static final String[] WIFI_ARR = {"getMacAddress", "getBSSID", "getIpAddress", "getNetworkId", "getSSID", "getRssi"};

    /*wifi扫描信息*/
    public static final String[] WIFI_SCAN = {"ScanResults.BSSID", "ScanResults.capabilities", "ScanResults.frequency", "ScanResults.level", "ScanResults.SSID"};

    /*分辨率*/
    public static final String[] DISPLAY_ARR = {"getWidth", "getHeight", "getRotation"};

    /*屏幕度量信息*/
    public static final String[] DISPLAY_METRICS = {"widthPixels", "heightPixels", "density", "densityDpi", "scaledDensity"};

    /*定位信息*/
    public static final String[] LOCATION = {"getLatitude", "getLongitude"};
}

