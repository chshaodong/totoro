package com.fx.totoro.hook;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.util.Log;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook;
import com.fx.totoro.utils.Constant;

/**
 * Created by chenshaodong on 2018/6/4.
 */

public class DisplayHook extends HookBase {

    public DisplayHook(XSharedPreferences xsp) {
        super(xsp);
    }

    public void hook() {
        try {
            for (String d : Constant.DISPLAY_ARR) {
                if (null == this.xsp.getString(d, null)) {
                    continue;
                }
                this.hookMethod(Display.class, d, Integer.parseInt(this.xsp.getString(d, "0")));
            }

            if (this.xsp.getString("widthPixels", null) != null &&
                    this.xsp.getString("heightPixels", null) != null) {
                this.hookDisplayMetrics(this.xsp);
            }
        } catch(Throwable e) {
            Log.e("Display Hook", e.getMessage());
        }
    }

    public void hookGetMetrics(final XSharedPreferences xsp) throws Throwable {
        XposedHelpers.findAndHookMethod(Display.class, "getMetrics", DisplayMetrics.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
                // TODO Auto-generated method stub
                super.afterHookedMethod(param);
                DisplayMetrics metrics = (DisplayMetrics)param.args[0];
                metrics.widthPixels = Integer.parseInt(xsp.getString("widthPixels", "0"));
                metrics.heightPixels = Integer.parseInt(xsp.getString("heightPixels", "0"));
                metrics.density = Float.parseFloat(xsp.getString("density", "1.5"));
                metrics.densityDpi = Integer.parseInt(xsp.getString("densityDpi", "240"));
                metrics.scaledDensity = Float.parseFloat(xsp.getString("scaledDensity", "1.5"));
                param.setResult(metrics);
            }

        });
    }

    public void hookRealMetrics(final XSharedPreferences xsp) throws Throwable {
        XposedHelpers.findAndHookMethod(Display.class, "getRealMetrics", DisplayMetrics.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param)
                    throws Throwable {
                // TODO Auto-generated method stub
                super.afterHookedMethod(param);
                DisplayMetrics metrics = (DisplayMetrics)param.args[0];
                metrics.widthPixels = Integer.parseInt(xsp.getString("widthPixels", "0"));
                metrics.heightPixels = Integer.parseInt(xsp.getString("heightPixels", "0"));
                metrics.density = Float.parseFloat(xsp.getString("density", "1.5"));
                metrics.densityDpi = Integer.parseInt(xsp.getString("densityDpi", "240"));
                metrics.scaledDensity = Float.parseFloat(xsp.getString("scaledDensity", "1.5"));
                param.setResult(metrics);
            }

        });
    }

    public void hookDisplayMetrics(final XSharedPreferences xsp) throws Throwable {
        XposedHelpers.findAndHookMethod(Resources.class, "getDisplayMetrics", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                    throws Throwable {
                super.afterHookedMethod(methodHookParam);
                DisplayMetrics metrics = new DisplayMetrics();
                metrics.widthPixels = Integer.parseInt(xsp.getString("widthPixels", "0"));
                metrics.heightPixels = Integer.parseInt(xsp.getString("heightPixels", "0"));
                metrics.density = Float.parseFloat(xsp.getString("density", "1.5"));
                metrics.densityDpi = Integer.parseInt(xsp.getString("densityDpi", "240"));
                metrics.scaledDensity = Float.parseFloat(xsp.getString("scaledDensity", "1.5"));
                methodHookParam.setResult(metrics);
            }
        });
    }
}
