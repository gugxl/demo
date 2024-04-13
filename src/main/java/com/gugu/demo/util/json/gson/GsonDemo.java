package com.gugu.demo.util.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author Administrator
 * @Classname GsonDemo
 * @Description TODO
 * @Date 2021/9/4 8:59
 */
public class GsonDemo {
    static User user = new User();
    static {
        user.setUsername("小谷");
        user.setAge(18);
        user.setGender(1);
        user.setGenderDesc("男");
    }

    public static void main(String[] args) {
//        test1();
//        exposeTest();
//        testBulider();
//        testException();
//        testNoException();

    }

    static class IntegerDefault0Adapter implements JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return json.getAsInt();
            }catch (NumberFormatException e){
                return 0;
            }
        }
    }
    private static void testNoException() {
        String str = "{\"gender\":\"\",\"user_name\":\"小谷\"}";
        User user = new GsonBuilder().registerTypeAdapter(int.class, new IntegerDefault0Adapter()).create().fromJson(str, User.class);
        System.out.println(user);
    }

    private static void testException() {
        String str = "{\"gender\":\"\",\"user_name\":\"小谷\"}";
        User user = new GsonBuilder().create().fromJson(str, User.class);
        // com.google.gson.JsonSyntaxException: java.lang.NumberFormatException: empty String
        // "" 转 int 异常
        System.out.println(user);
    }

    private static void testBulider() {
        String s = new GsonBuilder().create().toJson(new User("小谷", 18));
        System.out.println(s);
        // {"USER_NAME":"小谷","age":18,"gender":0}
        String s2 = new GsonBuilder().serializeNulls().create().toJson(new User("小谷", 18));
        System.out.println(s2);
        // {"USER_NAME":"小谷","age":18,"gender":0,"genderDesc":null}
    }

    private static void exposeTest() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String userStr = gson.toJson(user);
        System.out.println(userStr);
        // {"USER_NAME":"小谷","gender":1,"genderDesc":"男"}
        User user1 = gson.fromJson(userStr, User.class);
        System.out.println(user1);
        //User(username=小谷, age=0, gender=1, genderDesc=null)
    }

    private static void test1() {
        Gson gson = new Gson();
        String userStr = gson.toJson(user);
        System.out.println(userStr);
        User user1 = gson.fromJson(userStr, User.class);
        System.out.println(user1);
    }
}
