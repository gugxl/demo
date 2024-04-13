package com.gugu.demo.util.base64;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class CodecTest {
    public static void main(String[] args) {
        String str = "小谷";
        String encodeTest = encodeTest(str);
        System.out.println(encodeTest);
        String decodeTest = decodeTest(encodeTest);
        System.out.println(decodeTest);
    }

    //Base64编解码
    private static String encodeTest(String str){
        Base64 base64 = new Base64();
        try {
            str = base64.encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String decodeTest(String str){
        Base64 base64 = new Base64();
        //str = Arrays.toString(Base64.decodeBase64(str));
        return new String(Base64.decodeBase64(str));
    }
}
