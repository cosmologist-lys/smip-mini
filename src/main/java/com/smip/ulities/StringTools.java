package com.smip.ulities;

import java.security.MessageDigest;

import static oracle.jdbc.driver.OracleLog.byteToHexString;

/**
 * Created by kepler@gmail.com on 2017/11/9.
 */
public class StringTools {

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    public static String md5(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes()));
        } catch (Exception ex) {
        }

        return resultString;
    }

}
