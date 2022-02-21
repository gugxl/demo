package com.gugu.demo.sugar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Administrator
 * @Classname ResourcesTest
 * @Description TODO
 * @Date 2022/2/21 23:27
 */
public class ResourcesTest {
//    public static void main(String[] args) {
//        BufferedReader br = null;
//        try {
//            String line = null;
//            File file;
//            br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Test.txt"),"GB2312"));
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            // handle exception
//        } finally {
//            try {
//                if (br != null){
//                    br.close();
//                }
//            } catch (IOException e) {
//                // handle exception
//            }
//        }
//    }
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Test.txt"),"GB2312"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e) {
            // handle exception
        }
    }
}
