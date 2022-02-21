package com.gugu.demo.sugar;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator
 * @Classname TDemo
 * @Description TODO
 * @Date 2022/2/18 22:02
 */
public class TDemo {
    public static void main(String[] args) {
        EnumDemo.SPRING.name();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "gugu");
        map.put("wechat", "xiaogu");
        map.put("blog", "https://blog.csdn.net/zhazhagu");

    }

    public static <A extends Comparable<A>> A max(Collection<A> xs) {
        Iterator<A> xi = xs.iterator();
        A w = xi.next();
        while (xi.hasNext()) {
            A x = xi.next();
            if (w.compareTo(x) < 0)
                w = x;
        }
        return w;
    }

}


