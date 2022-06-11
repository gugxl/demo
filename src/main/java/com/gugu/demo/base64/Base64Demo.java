package com.gugu.demo.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author gugu
 * @Classname Base64Demo
 * @Description java8新特性支持base64类
 * @Date 2022/6/11 11:49
 */
public class Base64Demo {
    public static void main(String[] args) {
        String str = "hello gugu";
        String encoded = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);
        String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}
