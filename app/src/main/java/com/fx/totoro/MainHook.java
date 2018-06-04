package com.fx.totoro;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.util.Log;

import com.fx.totoro.hook.BuildHook;
import com.fx.totoro.utils.Constant;
import com.fx.totoro.hook.TelephonyHook;
import com.fx.totoro.hook.DisplayHook;
import com.fx.totoro.hook.NetworkHook;
import com.fx.totoro.hook.WifiHook;
import com.fx.totoro.hook.LocationHook;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("hook:", "start!");
        XSharedPreferences xsp = new XSharedPreferences(Constant.PKG_NAME, Constant.XSP_FILE);
        new BuildHook(xsp).hook();
        new TelephonyHook(xsp).hook();
        new DisplayHook(xsp).hook();
        new NetworkHook(xsp).hook();
        new WifiHook(xsp).hook();
        new LocationHook(xsp).hook();
        Log.i("hook:", "end!");
    }
}
