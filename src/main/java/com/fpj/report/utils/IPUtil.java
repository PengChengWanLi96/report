package com.fpj.report.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author fangpengjun
 * @date 2025/3/30
 */
public class IPUtil {
    public static String getServerIP() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}