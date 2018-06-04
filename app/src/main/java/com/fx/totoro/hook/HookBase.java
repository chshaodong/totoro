package com.fx.totoro.hook;

import android.util.Log;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook;

/**
 * Created by chenshaodong on 2018/6/4.
 */

public class HookBase {

    XSharedPreferences xsp = null;

    public HookBase(XSharedPreferences xsp) {
        this.xsp = xsp;
    }

    void hookMethod(Class cls, String methodName, final String result) {
        try {
            XposedHelpers.findAndHookMethod(cls, methodName, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                        throws Throwable {
                    methodHookParam.setResult(result);
                }
            });
        } catch (Throwable t) {
            Log.e("hook method", t.getMessage());
        }
    }

    void hookMethod(Class cls, String methodName, final int result) {
        try {
            XposedHelpers.findAndHookMethod(cls, methodName, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                        throws Throwable {
                    methodHookParam.setResult(result);
                }
            });
        } catch (Throwable t) {
            Log.e("hook method", t.getMessage());
        }
    }

    void hookMethod(Class cls, String methodName, final long result) {
        try {
            XposedHelpers.findAndHookMethod(cls, methodName, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                        throws Throwable {
                    methodHookParam.setResult(result);
                }
            });
        } catch (Throwable t) {
            Log.e("hook method", t.getMessage());
        }
    }

    void hookMethod(Class cls, String methodName, final byte[] result) {
        try {
            XposedHelpers.findAndHookMethod(cls, methodName, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                        throws Throwable {
                    methodHookParam.setResult(result);
                }
            });
        } catch (Throwable t) {
            Log.e("hook method", t.getMessage());
        }
    }

    void hookMethod(Class cls, String methodName, final double result) {
        try {
            XposedHelpers.findAndHookMethod(cls, methodName, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                        throws Throwable {
                    methodHookParam.setResult(result);
                }
            });
        } catch (Throwable t) {
            Log.e("hook method", t.getMessage());
        }
    }

    void hookMethod(Class cls, String methodName, final boolean result) {
        try {
            XposedHelpers.findAndHookMethod(cls, methodName, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                        throws Throwable {
                    methodHookParam.setResult(result);
                }
            });
        } catch (Throwable t) {
            Log.e("hook method", t.getMessage());
        }
    }
}
