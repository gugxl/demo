package com.gugu.demo.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @Classname Demo
 * @Description TODO
 * @Date 2021/12/1 22:55
 */
public class Demo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("asdc123","123abc","321abc");
        System.out.println(removEque(list));
    }



    public static List<String> removEque(List<String> list) {
        List<String> result = new ArrayList<>();
        // 利用map去重
        Map<String, String> tmp = new HashMap<>();
        for (String s : list) {
            tmp.put(getNumStr(s), s);
        }

        for (Map.Entry<String, String> entry :tmp.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * @Description 获取字符串中的数值[String]
     * @param str
     * @return java.lang.String
     * @auther Administrator
     */
    
    private static String getNumStr(String str) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }


}
