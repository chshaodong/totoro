package com.fx.totoro.hook;

import de.robv.android.xposed.XSharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.fx.totoro.utils.Constant;
import com.fx.totoro.utils.Common;

/**
 * Created by chenshaodong on 2018/6/4.
 */

public class TelephonyHook extends HookBase {

    public TelephonyHook(XSharedPreferences xsp) {
        super(xsp);
    }

    public void hook() {
        try {
            /*hook telephone相关信息*/
            for (String t : Constant.TEL_ARR) {
                if (null == this.xsp.getString(t, null)) {
                    continue;
                }
                String intKey[] = {"getNetworkType", "getDataActivity", "getPhoneType", "getSimState"};
                if (Common.isStrInArray(t, intKey)) {
                    this.hookMethod(TelephonyManager.class, t, Integer.parseInt(this.xsp.getString(t, "0")));
                } else if("hasIccCard".equals(t)) {
                    this.hookMethod(TelephonyManager.class, t, Boolean.parseBoolean(this.xsp.getString(t, "true")));
                } else {
                    this.hookMethod(TelephonyManager.class, t, this.xsp.getString(t, ""));
                }
            }
        } catch(Throwable e) {
            Log.e("Telephony Hook", e.getMessage());
        }
    }
}
