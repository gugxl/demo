package com.gugu.demo.other;

public class LaoWang implements Person {

    private String name = "小王";

    public LaoWang() {
    }

    @Override
    public void getMessage(String s) {
        System.out.println(name + "接到了小美打过来的电话，电话内容是：" + s);
    }

}


