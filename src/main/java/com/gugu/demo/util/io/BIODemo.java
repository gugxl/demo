package com.gugu.demo.util.io;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIODemo {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(128);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(1234));
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new ConnectTask(socket));
        }
    }
}

class ConnectTask extends Thread {
    private Socket socket;

    public ConnectTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try(InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream()) {
                int read = inputStream.read();
                System.out.println(read);
                outputStream.write(read);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Client {
    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket("127.0.0.1", 1234);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello".getBytes());
        outputStream.close();
        socket.close();

    }
}

