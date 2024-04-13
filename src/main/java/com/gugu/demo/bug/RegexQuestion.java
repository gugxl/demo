package com.gugu.demo.bug;

/**
 * @author gugu
 * @Classname RegexQuestion
 * @Description 正则表达式可能回溯会导出cpu打满
 * @Date 2022/6/22 22:16
 */
public class RegexQuestion {
    private static String regex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$";

//    private static String regex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~_%\\/])+$"; // 1. 加上_%提前终止回溯
//    private static String regex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)++"; // 2. 不改变原有含义

    public static void main(String[] args) {
        String url = "http://www.javastack.cn/dzfp-web/pdf/download?request=6e7JGm38jfjghVrv4ILd-kEn64HcUX4qL4a4qJ4-CHLmqVnenXC692m74H5oxkjgdsYazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        if (url.matches(regex)){
            System.out.println("match");
        }else {
            System.out.println("not match");
        }
    }
}
