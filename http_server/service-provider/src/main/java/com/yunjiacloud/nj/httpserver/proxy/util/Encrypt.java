package com.yunjiacloud.nj.httpserver.proxy.util;

public class Encrypt {

    public static final int pass1 = 0x0a;
    public static final int pass2 = 0x01;

    public static String DoEncrypt(String str) {
        int i;
        int tmpch;
        StringBuilder enStrBuff = new StringBuilder();
        for (i = 0; i < str.length(); i++) {
            tmpch = (int) str.charAt(i);

            tmpch = tmpch ^ pass2;
            tmpch = tmpch ^ pass1;

            enStrBuff.append(Integer.toHexString(tmpch));
        }

        return enStrBuff.toString().toUpperCase();
    }

    public static String DoDecrypt(String str) {

        int tmpch;
        String deStr = str.toLowerCase();
        StringBuilder deStrBuff = new StringBuilder();
        for (int i = 0; i < deStr.length(); i += 2) {
            String subStr = deStr.substring(i, i + 2);
            tmpch = Integer.parseInt(subStr, 16);
            tmpch = tmpch ^ pass2;
            tmpch = tmpch ^ pass1;
            deStrBuff.append((char) tmpch);
        }
        return deStrBuff.toString();
    }

}
