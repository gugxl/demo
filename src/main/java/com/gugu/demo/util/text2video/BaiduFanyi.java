package com.gugu.demo.util.text2video;

import javazoom.jl.converter.Converter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaiduFanyi {
    @SneakyThrows
    public static void main(String[] args) {
        String str = "物理学中的四大神兽" +
            "物理学中的四大神兽是经典的科学难题和哲学问题的象征，它包括薛定谔的猫、芝诺悖论、拉普拉斯妖、麦克斯韦妖。1、薛定谔的猫，即一只黑箱中的猫，在打开箱子以前处于既生又死的状态，它代表了量子力学中的超定态问题，仍有争议;2、芝诺的乌龟，它根据一定条件提出，如果时间可以无限地分割，人可能永远也无法追上乌龟的悖论，这是关于无穷性和极限的问题，微积分已解决;3、拉普拉斯的妖，它想探寻一个超级智慧的存在能否探寻往昔预测未来，涉及决定论和因果性，受到了混沌理论的挑战;" +
            "4、麦克斯韦的妖，它认为一个虚构的麦克斯韦的妖可以打破热力学原理，尚未违反。总的来说，这些神兽在科学界引发了深刻的思考和讨论。";
        getMp3(str);
        String[] split = str.split("。");
        mp32wav();
        runBat();
    }

    private static void getMp3(String str) throws Exception {
        System.out.println(str);
        String url = "https://fanyi.baidu.com/gettts?lan=zh&text=" + str + "&spd=5&source=web";
        URL obj = new URL(url);

        // 创建HttpURLConnection对象
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setConnectTimeout(3 * 1000);
        // 设置请求方法为GET
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // 获取响应码
        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // 读取响应内容
        InputStream inputStream = con.getInputStream();
        File file = new File("ttt.mp3");
        copyInputStreamToFile(inputStream, file);
        inputStream.close();
    }

    private static void mp32wav() throws Exception {
        Converter converter = new Converter();
        converter.convert("D:\\applicationfiles\\IDEA\\demo\\ttt.mp3", "D:\\applications\\tools\\JHZHuman\\test.wav");
    }


    private static void runBat() throws Exception {
        String command = "D:/applications/tools/JHZHuman/SadTalker.bat";
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File("D:/applications/tools/JHZHuman/"));
        pb.command(command);
        pb.redirectErrorStream(true);
        // 启动进程
        Process process = pb.start();

        // 创建线程处理子进程的输出流和错误流
        StreamGobbler inputStreamGobbler = new StreamGobbler(process.getInputStream(), "INPUT");
        StreamGobbler errorStreamGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");

        // 启动线程
        inputStreamGobbler.start();
        errorStreamGobbler.start();


        // 等待进程执行完毕
        int exitCode = process.waitFor();
        // 等待线程执行完毕
        inputStreamGobbler.join();
        errorStreamGobbler.join();

        System.out.println("批处理脚本执行完毕，退出码：" + exitCode);
    }

    private static class StreamGobbler extends Thread {
        private final InputStream stream;
        private final String type;

        public StreamGobbler(InputStream stream, String type) {
            this.stream = stream;
            this.type = type;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "GBK"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理流中的每一行数据
//                    System.out.println(type + ": " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

}
