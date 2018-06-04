package com.fx.totoro.hook;

import android.annotation.SuppressLint;
import android.net.NetworkInfo;
import android.util.Log;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import com.fx.totoro.utils.Constant;
import com.fx.totoro.utils.Common;
import java.net.InetAddress;

/**
 * Created by chenshaodong on 2018/6/4.
 */

public class NetworkHook extends HookBase {

    public NetworkHook(XSharedPreferences xsp) {
        super(xsp);
    }

    public void hook() {
        try {
            for (String n : Constant.NETWORK_ARR) {
                if (null == this.xsp.getString(n, null)) {
                    continue;
                }
                String intKey[] = {"getSubtype", "getType"};
                if (Common.isStrInArray(n, intKey)) {
                    this.hookMethod(NetworkInfo.class, n, Integer.parseInt(this.xsp.getString(n, "0")));
                } else {
                    this.hookMethod(NetworkInfo.class, n, this.xsp.getString(n, ""));
                }
            }

            for (String i : Constant.INET_ARR) {
                if (null == this.xsp.getString(i, null)) {
                    continue;
                }
                if ("getAddress".equals(i)) {
                    this.hookMethod(InetAddress.class, i, Common.ipToByte(this.xsp.getString(i, null)));
                } else {
                    this.hookMethod(InetAddress.class, i, this.xsp.getString(i, ""));
                }
            }
//        this.hookLocalHost(this.xsp);
        } catch(Throwable e) {
            Log.e("Network Hook", e.getMessage());
        }
    }

    public void hookLocalHost(final XSharedPreferences xsp) throws Throwable {
        XposedHelpers.findAndHookMethod(InetAddress.class, "getLocalHost", new XC_MethodHook() {
            @SuppressLint({"NewApi"})
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                    throws Throwable {
                super.afterHookedMethod(methodHookParam);
                if(null != xsp.getString("getHostAddress", null)) {
                    methodHookParam.setResult(InetAddress.getByName(xsp.getString("getHostAddress", "")));
                }
            }
        });
    }
}
