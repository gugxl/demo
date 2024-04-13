package com.gugu.demo.util.zerocopy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author Administrator
 * @Classname MappedByteBufferTest
 * @Description TODO
 * @Date 2021/9/9 0:44
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D://LICENSE.txt");
        long length = file.length();
        byte[] ds = new byte[(int) length];
        MappedByteBuffer map = new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY, 0, length);
        for (int i = 0; i < length; i++) {
            byte b = map.get();
            ds[i] =b;
        }
        Scanner scanner = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
        while (scanner.hasNext()){
            System.out.println(scanner.next() + " ");
        }
    }
}
