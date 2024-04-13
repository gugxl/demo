package com.gugu.demo.util.base64;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author gugu
 * @Classname Base64Demo
 * @Description java8新特性支持base64类
 * @Date 2022/6/11 11:49
 */
public class Base64Demo {
    static String str = "hello gugu";

    public static void main(String[] args) throws IOException {
        // 性能不好
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        BASE64Decoder base64Decoder = new BASE64Decoder();
//        String encode = base64Encoder.encode(str.getBytes(StandardCharsets.UTF_8));
//        System.out.println(encode);
//        String decode = new String(base64Decoder.decodeBuffer(encode), StandardCharsets.UTF_8);
//        System.out.println(decode);

        // 推荐
        String encoded = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);
        String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}
