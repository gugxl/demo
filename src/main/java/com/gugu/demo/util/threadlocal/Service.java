package com.gugu.demo.util.threadlocal;

/**
 * @author gugu
 * @Classname Service
 * @Description TODO
 * @Date 2022/11/14 22:15
 */
public class Service {
//    private static ThreadLocal<Integer> requestIdThreadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        Integer reqId = 5;
        Service service = new Service();
        service.setRequestId(reqId);
    }

    public void setRequestId(Integer reqId){
        requestIdThreadLocal.set(reqId);
        doBussiness();
    }

    public void doBussiness(){
        System.out.println("首先打印requestId:" + requestIdThreadLocal.get());
        (new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程启动");
                System.out.println("在子线程中访问requestId:" + requestIdThreadLocal.get());
            }
        })).start();
    }
}
