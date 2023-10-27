package com.gugu.demo.text2vedio;

import javazoom.jl.converter.Converter;
import lombok.SneakyThrows;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaiduFanyi {
    @SneakyThrows
    public static void main(String[] args) {
        String str = "《肖申克的救赎》是由弗兰克·德拉邦特执导，根据斯蒂芬·金的同名小说改编而成的一部剧情电影。该片于1994年上映，以其深刻的情感、真实的人物形象和引人入胜的剧情而广受好评。" +
            "电影讲述了主人公安迪·杜弗雷恩（由蒂姆·罗宾斯饰演）因涉嫌谋杀妻子和她的情人而被判无期徒刑，被送往肖申克监狱服刑。在监狱中，安迪逐渐与其他囚犯建立了关系，尤其是红（由摩根·弗里曼饰演），他成为了他的密友。" +
            "在监狱中，安迪通过自己的智慧和才能，帮助了监狱管理层进行各种活动，例如财务规划和图书馆建设等。他还通过音乐和知识向其他囚犯传递了希望和启示，使他们在绝望的环境中找到了生活的意义。" +
            "整部电影贯穿着对人性的思考和追求自由的主题。安迪展示了坚持信念、不屈不挠的精神，通过智慧和意志力战胜了困境，并最终实现了自己的救赎。" +
            "《肖申克的救赎》被广大观众誉为一部经典之作，不仅在票房上取得成功，还获得了多项奖项提名和赞誉。它以其深入人心的故事和优秀的表演成为了电影史上的经典之作，并继续影响着观众的情感和思考。";
        getMp3(str);        String[] split = str.split("。");

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
