package com.gugu.demo.util.classload;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author gugu
 * @Classname LocalClassLoader
 * @Description 自定义classload
 * @Date 2022/6/12 13:43
 */
public class LocalClassLoader extends ClassLoader {
    private static String path = "D:\\applicationfiles\\IDEA\\demo\\src\\main\\java";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findClass(name);
        if (aClass != null){
            return aClass;
        }
        if (!name.endsWith(".User")){
            return super.findClass(name);
        }

        try {
            InputStream is = new FileInputStream(path + name.replace(".", "/") + ".class");
            byte[] bytes = IOUtils.toByteArray(is);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

//    @SneakyThrows
//    public static void main(String[] args) {
//        LocalClassLoader localClassLoader = new LocalClassLoader();
//        Class<?> aClass = localClassLoader.loadClass("com.gugu.demo.serializable.User");
//        Field field = FieldUtils.getField(aClass, "userName", true);
//        Object value = field.get(aClass.newInstance());
//        System.out.println(value);
//    }


    // 自带的
    @SneakyThrows
    public static void main(String[] args) {
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new File("path").toURI().toURL()});
        Class<?> aClass = urlClassLoader.loadClass("com.gugu.demo.serializable.User");
        Field field = FieldUtils.getField(aClass, "userName", true);
        Object value = field.get(aClass.newInstance());
        System.out.println(value);
    }
}
