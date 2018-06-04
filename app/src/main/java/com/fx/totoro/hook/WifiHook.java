package com.fx.totoro.hook;

import android.annotation.SuppressLint;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.fx.totoro.utils.Constant;
import com.fx.totoro.utils.Common;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by chenshaodong on 2018/6/4.
 */

public class WifiHook extends HookBase {

    public WifiHook(XSharedPreferences xsp) {
        super(xsp);
    }

    public void hook() {
        try {
            for (String n : Constant.WIFI_ARR) {
                if (null == this.xsp.getString(n, null)) {
                    continue;
                }
                String intKey[] = {"getIpAddress", "getNetworkId", "getRssi"};
                if (Common.isStrInArray(n, intKey)) {
                    this.hookMethod(WifiInfo.class, n, Integer.parseInt(this.xsp.getString(n, "0")));
                } else {
                    this.hookMethod(WifiInfo.class, n, this.xsp.getString(n, ""));
                }
            }

            /*hook wifi扫描信息*/
            if(xsp.getString("ScanResults.BSSID", null) != null) {
                this.hookWifiScanResults(this.xsp);
            }
        } catch(Throwable e) {
            Log.e("Wifi Hook", e.getMessage());
        }
    }

    /**
     *
     * hook wifi扫描信息，如果要数据比较真实需要设置多个不同的wifi信息
     *
     * */
    public void hookWifiScanResults(final XSharedPreferences xsp) {
        try {
            XposedHelpers.findAndHookMethod(WifiManager.class, "getScanResults", new XC_MethodHook() {

                @SuppressLint({"NewApi"})
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                        throws Throwable {
                    super.afterHookedMethod(methodHookParam);
                    ArrayList localArrayList = (ArrayList)methodHookParam.getResult();
                    ((ScanResult)localArrayList.get(0)).BSSID = xsp.getString("ScanResults.BSSID", "");
                    ((ScanResult)localArrayList.get(0)).capabilities = xsp.getString("ScanResults.capabilities", "");
                    ((ScanResult)localArrayList.get(0)).frequency = Integer.parseInt(xsp.getString("ScanResults.frequency", "0"));
                    ((ScanResult)localArrayList.get(0)).level = Integer.parseInt(xsp.getString("ScanResults.level", "0"));
                    ((ScanResult)localArrayList.get(0)).SSID = xsp.getString("ScanResults.SSID", "");
                    methodHookParam.setResult(localArrayList);
                }
            });
        } catch (Throwable t) {
            Log.e("hookWifiManager", t.getMessage());
        }
    }
}
