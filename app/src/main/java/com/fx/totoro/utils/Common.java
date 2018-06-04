package com.fx.totoro.utils;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by chenshaodong on 2018/6/4.
 */

public class Common {

    /*执行linux命令获取root权限*/
    public static int execRootCmdSilent(String cmd) {
        int result = -1;
        DataOutputStream dos = null;

        try {
            Process p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());

            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            p.waitFor();
            result = p.exitValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static boolean isStrInArray(String str, String arr[]) {
        if(str == null) return false;
        for(String a: arr) {
            if(str.equals(a)) return true;
        }
        return false;
    }

    public static byte[] ipToByte(String ip) {
        if(ip == null) return null;
        byte[] ipByte = new byte[4];
        String[] ipArr = ip.split(".");
        if(ipArr.length != 4) {
            return null;
        }
        for(int i = 0; i < ipArr.length; i++) {
            try {
                byte sub = Byte.parseByte(ipArr[i]);
                if(sub <= 0 || sub > 255) {
                    return null;
                }
                ipByte[i] = sub;
            } catch(Exception e) {
                return null;
            }
        }
        return ipByte;
    }
}
