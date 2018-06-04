package com.fx.totoro;

import android.os.Bundle;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.Context;
import com.fx.totoro.utils.Constant;
import com.fx.totoro.utils.Common;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("page:", "start");

        /*执行命令获取root权限*/
        Common.execRootCmdSilent("ls");

        SharedPreferences xsp = getSharedPreferences(Constant.XSP_FILE, Context.MODE_WORLD_READABLE);
        Editor xspEdit = xsp.edit();
        xspEdit.clear();
        xspEdit.commit();

        for(String b: Constant.BUILD_ARR) {
            xspEdit.putString(b, "111");
        }

        for(String b: Constant.BUILD_METHOD) {
            xspEdit.putString(b, "44444");
        }

        for(String v: Constant.VERSION_ARR) {
            xspEdit.putString(v,"34");
        }

        for(String t: Constant.TEL_ARR) {
            xspEdit.putString(t,"3");
        }

        for(String t: Constant.NETWORK_ARR) {
            xspEdit.putString(t,"4");
        }

        for(String t: Constant.INET_ARR) {
            xspEdit.putString(t,"127.0.0.1");
        }

        for(String t: Constant.WIFI_ARR) {
            xspEdit.putString(t,"5");
        }

        for(String t: Constant.WIFI_SCAN) {
            xspEdit.putString(t,"6");
        }

        for(String t: Constant.DISPLAY_ARR) {
            xspEdit.putString(t,"500");
        }

        String[] DISPLAY_METRICS = {"widthPixels", "heightPixels", "density", "densityDpi", "scaledDensity"};
        xspEdit.putString("widthPixels", "540");
        xspEdit.putString("heightPixels", "960");
        xspEdit.putString("density", "1.5");
        xspEdit.putString("densityDpi", "240");
        xspEdit.putString("scaledDensity", "1.5");

        for(String t: Constant.LOCATION) {
            xspEdit.putString(t,"12009");
        }
        xspEdit.putString("androidId", "2323bn");
        xspEdit.commit();
        Log.i("page:", "end");
    }
}
