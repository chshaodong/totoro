package com.fx.totoro.hook;

import android.content.ContentResolver;
import android.os.Build;
import android.util.Log;
import android.provider.Settings;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook;
import com.fx.totoro.utils.Constant;
import android.content.res.Resources;

/**
 * Created by chenshaodong on 2018/6/3.
 */

public class BuildHook extends HookBase {

    public BuildHook(XSharedPreferences xsp) {
        super(xsp);
    }

    public void hook() {

        try {
            /*hook系统以及硬件信息*/
            for (String b : Constant.BUILD_ARR) {
                if (null == this.xsp.getString(b, null)) {
                    continue;
                }
                if ("TIME".equals(b)) {
                    XposedHelpers.setStaticObjectField(Build.class, b, Long.parseLong(this.xsp.getString(b, "0")));
                } else {
                    XposedHelpers.setStaticObjectField(Build.class, b, this.xsp.getString(b, ""));
                }
            }

            /*hook版本信息*/
            for (String v : Constant.VERSION_ARR) {
                if (null == this.xsp.getString(v, null)) {
                    continue;
                }
                if ("SDK_INT".equals(v)) {
                    XposedHelpers.setStaticObjectField(Build.VERSION.class, v, Integer.parseInt(this.xsp.getString(v, "0")));
                } else {
                    XposedHelpers.setStaticObjectField(Build.VERSION.class, v, this.xsp.getString(v, ""));
                }
            }

            /*hook method*/
            for (String m : Constant.BUILD_METHOD) {
                if (null == this.xsp.getString(m, null)) {
                    continue;
                }
                this.hookMethod(Build.class, m, this.xsp.getString(m, ""));
            }

            this.hookAndroidId(this.xsp);
        } catch(Throwable e) {
            Log.e("Build Hook", e.getMessage());
        }
    }

    public void hookAndroidId(final XSharedPreferences xsp) throws Throwable {
        XposedHelpers.findAndHookMethod(Settings.Secure.class, "getString", new Object[] { ContentResolver.class, String.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                    throws Throwable {
                if(methodHookParam.args[1] == "android_id"
                        && xsp.getString("androidId", null) != null) {
                    methodHookParam.setResult(xsp.getString("androidId", null));
                }
            }
        }});
    }
}
