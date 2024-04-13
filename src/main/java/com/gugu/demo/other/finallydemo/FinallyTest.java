package com.gugu.demo.other.finallydemo;


/**
 * @author Administrator
 * @Classname FinallyTest
 * @Description TODO
 * @Date 2022/5/29 11:13
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
    }

    public static int test1() {
        int i = 1;
        try {
            return i;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            i = 0;
        }
        return i;
    }
    public static int test2() {
        int i = 1;
        try {
            return i;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            i = 0;
            return i;
        }
    }
    public static UserDO test3() {
        UserDO user = new UserDO( 10L, "gugu");
        try {
            return user;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            user = new UserDO(20L, "gugu");
        }
        // 执行不到
        return null;
    }
    public static UserDO test4() {
        UserDO user = new UserDO( 10L, "gugu");
        try {
            return user;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            user = new UserDO(20L, "gugu");
            return user;
        }
    }
}
