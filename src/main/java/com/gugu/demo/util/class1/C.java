package com.gugu.demo.util.class1;

/**
 * @author Administrator
 * @Classname C
 * @Description TODO
 * @Date 2021/10/17 11:59
 */
public class C extends B implements A {
    public static void main(String[] args) {
        C c = new C();
        c.pX();
    }
    public void pX(){
        // 如果只写x，无法确定是A接口中的还是B类中的会编译错误
        System.out.println(A.x);
    }
}
