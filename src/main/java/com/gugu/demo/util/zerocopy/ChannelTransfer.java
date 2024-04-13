package com.gugu.demo.util.zerocopy;

import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Administrator
 * @Classname ChannelTransfer
 * @Description TODO
 * @Date 2021/9/9 0:57
 */
public class ChannelTransfer {
    public static void main(String[] args) throws Exception {
        String filePath = "D://LICENSE.txt";
        catFiles(Channels.newChannel(System.out), filePath);
    }

    private static void catFiles(WritableByteChannel target, String filePath)
            throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        FileChannel channel = fis.getChannel();
        channel.transferTo(0, channel.size(), target);
        channel.close();
        fis.close();
    }
}
