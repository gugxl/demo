package com.gugu.demo.bug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Administrator
 * @Classname Name
 * @Description TODO
 * @Date 2022/3/6 18:04
 */
public class Name {
    private String first, last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        Name name = (Name) o;
        return name.first.equals(first) && name.last.equals(last);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(first, last);
//    }

    public static void main(String[] args) {
        Set s = new HashSet();
//        List s = new ArrayList();
//        Map<Name, String> s = new HashMap<>();
        s.add(new Name("Mickey","Mouse"));
        System.out.println(s.contains(new Name("Mickey","Mouse")));
    }
}
