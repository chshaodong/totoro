package com.fx.totoro.hook;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import android.os.Bundle;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.telephony.TelephonyManager;
import android.location.Location;
import com.fx.totoro.utils.Constant;

/**
 * Created by chenshaodong on 2018/6/4.
 */

public class LocationHook extends HookBase {

    public LocationHook(XSharedPreferences xsp) {
        super(xsp);
    }

    public void hook() {
        try {
            for(String l: Constant.LOCATION) {
                if(this.xsp.getString(l, null) == null) {
                    continue;
                }
                this.hookMethod(Location.class, l, Double.parseDouble(this.xsp.getString(l, "-1")));
            }
            this.hookCellLocation(this.xsp);
        } catch(Throwable e) {
            Log.e("Location Hook", e.getMessage());
        }
    }

    public void hookCellLocation(final XSharedPreferences xsp) throws Throwable {
        XposedHelpers.findAndHookMethod(TelephonyManager.class, "getCellLocation", new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam)
                    throws Throwable {

                super.afterHookedMethod(methodHookParam);
                String cellLocation = xsp.getString("getCellLocation", null);
                if(cellLocation == null) {
                    methodHookParam.setResult(null);
                    return;
                }
                String[] clArr = cellLocation.split(",");
                if(clArr.length != 5 && clArr.length != 3) {
                    methodHookParam.setResult(null);
                    return;
                }
                int[] intClArr = new int[clArr.length];
                for(int i = 0; i < clArr.length; i++) {
                    try {
                        intClArr[i] = Integer.parseInt(clArr[i]);
                    } catch(Throwable e) {
                        methodHookParam.setResult(null);
                        return;
                    }
                }
                if(intClArr.length == 5) {
                    CdmaCellLocation cdmb = new CdmaCellLocation();
                    cdmb.setCellLocationData(intClArr[0], intClArr[1], intClArr[2], intClArr[3], intClArr[4]);
                    methodHookParam.setResult(cdmb);
                } else if(intClArr.length == 3) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("lac", intClArr[0]);
                    bundle.putInt("cid", intClArr[1]);
                    bundle.putInt("psc", intClArr[2]);
                    methodHookParam.setResult(new GsmCellLocation(bundle));
                }
            }
        });
    }
}
