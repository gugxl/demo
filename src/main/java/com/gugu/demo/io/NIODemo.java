package com.gugu.demo.io;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIODemo {
    @SneakyThrows
    public static void main(String[] args) {
        // server 端
        // 获取一个serversocket通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(1234));
        // 获取通道管理器
        Selector selector = Selector.open();
        // 将通道和通道管理器绑定，并且为通道注册OP_ACCEPT事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }
}

class SeverClient {
    @SneakyThrows
    public static void main(String[] args) {
        while (true) {
            //当有注册的事件到达时，方法返回，否则阻塞。
            Selector selector = Selector.open();
            for (SelectionKey selectedKey : selector.selectedKeys()) {
                if (selectedKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectedKey.channel();
                    SocketChannel accept = server.accept();
                    accept.write(ByteBuffer.wrap(new String("send message to client").getBytes()));
                    //在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。
                    server.register(selector, SelectionKey.OP_READ);

                } else if (selectedKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectedKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    int read = channel.read(buffer);
                    byte[] data = buffer.array();
                    String msg = new String(data);
                    System.out.println("receive message from client, size:" + buffer.position() + " msg:" + msg);


                }
            }
        }
    }
}
