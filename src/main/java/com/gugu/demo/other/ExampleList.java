package com.gugu.demo.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Classname ExampleList
 * @Description TODO
 * @Date 2020/10/7 13:41
 */
public class ExampleList {
    static int length = 65536;
    public static List<String> listNoLength = new ArrayList<String>();
    public static List<String> listLength = new ArrayList<String>(length);
    public static void main(String[] args) {
        addList(0);
        addList(1);
    }

    public static void addList(int sign) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            if (sign == 0){
                listNoLength.add("asdf");
            }else {
                listLength.add("asdf");
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("sign:"+sign + " "+(end-start));
    }
}
