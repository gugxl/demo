package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

// 通过管道字节流/ 字符流也是一样的进行线程 通信
public class Demo12 {

    @SneakyThrows
    public static void main(String[] args) {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();

        outputStream.connect(inputStream);

        new ThreadRead(readData, inputStream).start();
        Thread.sleep(100);
        new ThreadWrite(writeData, outputStream).start();
    }

    static class ThreadWrite extends Thread {
        private WriteData writeData;
        private PipedOutputStream outputStream;

        public ThreadWrite(WriteData writeData, PipedOutputStream outputStream) {
            this.writeData = writeData;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            writeData.writeMethod(outputStream);
        }
    }

    static class ThreadRead extends Thread {
        private ReadData readData;
        private PipedInputStream pipedInputStream;

        public ThreadRead(ReadData readData, PipedInputStream pipedInputStream) {
            this.readData = readData;
            this.pipedInputStream = pipedInputStream;
        }

        @Override
        public void run() {
            readData.readMethod(pipedInputStream);
        }
    }

    static class WriteData {
        public void writeMethod(PipedOutputStream outputStream) {
            try {
                System.out.println("write:");
                for (int i = 0; i < 30; i++) {
                    String outData = "" + (i + 1);
                    outputStream.write(outData.getBytes());
                    System.out.print(outData);
                }
                outputStream.close();
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ReadData {
        public void readMethod(PipedInputStream inputStream) {
            try {
                System.out.println("read:");
                byte[] b = new byte[20];
                int readLength = inputStream.read(b);
                while (readLength != -1) {
                    String newData = new String(b, 0, readLength);
                    System.out.print(newData);
                    readLength = inputStream.read(b);
                }
                System.out.println();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
