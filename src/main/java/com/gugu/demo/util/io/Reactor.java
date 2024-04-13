package com.gugu.demo.util.io;


import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(1236)).start();
    }

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
                for (SelectionKey selectedKey : selector.selectedKeys()) {
                    dispatch(selectedKey);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private void dispatch(SelectionKey selectedKey) {
        Runnable runnable = (Runnable) selectedKey.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    class Acceptor implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            SocketChannel channel = ServerSocketChannel.open().accept();
            if (channel != null) {
                new Handler(selector, channel);
            }

        }
    }


    class Handler implements Runnable {

        private final static int DEFAULT_SIZE = 1024;
        private final SocketChannel socketChannel;
        private final SelectionKey selectionKey;
        private static final int READING = 0;
        private static final int SENDING = 1;

        private int state = READING;

        ByteBuffer inputBuffer = ByteBuffer.allocate(DEFAULT_SIZE);
        ByteBuffer outputBuffer = ByteBuffer.allocate(DEFAULT_SIZE);

        public Handler(Selector selector, SocketChannel channel) throws IOException {
            this.socketChannel = channel;
            socketChannel.configureBlocking(false);
            selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                write();
            }
        }

        class Sender implements Runnable {
            @Override
            public void run() {
                try {
                    socketChannel.write(outputBuffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (outIsComplete()) {
                    selectionKey.cancel();
                }
            }
        }

        private void write() {
            try {
                socketChannel.write(outputBuffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (outIsComplete()) {
                selectionKey.cancel();
            }
        }

        private void read() {
            try {
                socketChannel.read(inputBuffer);
                if (inputIsComplete()) {
                    process();
                    System.out.println("接收到来自客户端（" +
                        socketChannel.socket().getInetAddress().getHostAddress()
                        + "）的消息：" + new String(inputBuffer.array()));
                    selectionKey.attach(new Sender());
                    selectionKey.interestOps(selectionKey.OP_WRITE);
                    selectionKey.selector().wakeup();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean inputIsComplete() {
            return true;
        }

        public boolean outIsComplete() {
            return true;
        }

        public void process() {
            System.out.println("process ....");
        }
    }


}


