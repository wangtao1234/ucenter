package com.example.ucenter.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by admin on 2018/4/2.
 */
public class MyUtils {
        public static  String getMD5(String instr){
            MessageDigest md5 =null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return "";
            }
            char[] charArray = instr.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for(int i=0;i<charArray.length;i++){
                byteArray[i] = (byte) charArray[i];
            }
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<md5Bytes.length;i++){
                int val = ((int)md5Bytes[i]) & 0xff;
                if(val <16){
                    sb.append("0");
                    sb.append(Integer.toHexString(val));
                }
            }
            return sb.toString();
        }
    }
