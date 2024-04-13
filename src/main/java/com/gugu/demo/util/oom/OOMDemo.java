package com.gugu.demo.util.oom;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gugu
 * @Classname Main
 * @Description TODO
 * @Date 2022/9/4 9:15
 */
public class OOMDemo {
    public static void main(String[] args) {
//        new Main().fun1();
//        new Main().fun2();
//        new Main().fun3();
        new OOMDemo().fun4();
    }
    //强引用
    public void fun1(){
        Object o = new Object();
        Object[] objArr = new Object[100];
    }
    // 软引用内存不够就会回收
    public void fun2(){
        SoftReference<String> stringSoftReference = new SoftReference<String>(new String("11111"));
        System.out.println(stringSoftReference.get());
    }

    // 弱引用只要gc发现就会回收
    public void fun3(){
        WeakReference<String> weakReference = new WeakReference<String>(new String("11111"));
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }

    // 虚引用
    public void fun4(){
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<String>(new String("12121"), queue);
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());
        System.gc();
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());
    }

    private Map<String, SoftReference<File>> fileCache = new HashMap<String, SoftReference<File>>();
    public void addFileToCache(String path){
        // 强引用的File对象
        File file = new File(path);
        // 软引用的File对象
        SoftReference<File> fileSoftReference = new SoftReference<>(file);
        // 添加该对象到Map中使其缓存
        fileCache.put(path, fileSoftReference);
    }

    public File getBitmapByPath(String path){
        // 从缓存中取软引用的File对象
        SoftReference<File> fileSoftReference = fileCache.get(path);
        // 判断是否存在软引用
        if (fileSoftReference==null) {
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        File file = fileSoftReference.get();
        return file;
    }

}
