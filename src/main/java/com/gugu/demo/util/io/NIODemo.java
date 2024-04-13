package com.gugu.demo.util.io;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class NIODemo {
    @SneakyThrows
    public static void main(String[] args) {
        // server 端
        // 获取一个serversocket通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(1235));
        // 获取通道管理器
        Selector selector = Selector.open();
        // 将通道和通道管理器绑定，并且为通道注册OP_ACCEPT事件
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel1 = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1025);
                    FileChannel outChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                    while (channel1.read(byteBuffer) > 0) {
                        // 在读之前都要切换成读模式
                        byteBuffer.flip();

                        outChannel.write(byteBuffer);

                        // 读完切换成写模式，能让管道继续读取文件的数据
                        byteBuffer.clear();
                    }

                }
                selectionKeyIterator.remove();
            }

        }
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
