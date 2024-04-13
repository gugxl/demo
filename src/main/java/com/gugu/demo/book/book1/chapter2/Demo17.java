package com.gugu.demo.book.book1.chapter2;

import lombok.Data;

public class Demo17 {
    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        publicClass.setUserName("gugu");
        publicClass.setPassword("xiaogu");
        System.out.println(publicClass.getUserName() + " " + publicClass.getPassword());
        PublicClass.PrivateClass privateClass = publicClass.new PrivateClass();
        privateClass.setAge(100);
        privateClass.setAddress("HZ");
        System.out.println(privateClass.getAge() + " " + privateClass.getAddress());
    }

    @Data
    static class PublicClass {
        private String userName;
        private String password;

        @Data
        class PrivateClass {
            private int age;
            private String address;

            public void printPublicClass() {
                System.out.println("userName=" + userName + ", password=" + password);
            }
        }
    }
}


