package com.gugu.demo.util.sougouiamge;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SougouImgPipeline {
    private String extension = ".jpg";
    private String path;

    private volatile AtomicInteger suc;
    private volatile AtomicInteger fails;
    static Set<String> extSet = new HashSet<>();

    static {
        extSet.add(".jpg");
        extSet.add(".JPG");
        extSet.add(".jpeg");
        extSet.add(".JPEG");
        extSet.add(".png");
        extSet.add(".PNG");
    }

    // 黑名单，在列表里面的不下载
    static Set<String> blackSet = new HashSet<>();

    static {
        blackSet.add("tupian.1015600.com");
        blackSet.add("www.ggeye.com");
        blackSet.add("s9.rr.itc.cn");
    }

    public SougouImgPipeline() {
        setPath("D:\\applicationfiles\\data\\image");
        suc = new AtomicInteger();
        fails = new AtomicInteger();
    }

    public void setPath(String path) {
        this.path = path;
    }


    @SneakyThrows
    private void downloadImg(String url, String cate, String name) {
        if (blackSet.contains(new URL(url).getHost())) {
            System.out.println("黑名单：" + url);
        }
        String path = this.path + "/" + cate + "/";
        File dir = new File(path);
        if (!dir.exists()) {    // 目录不存在则创建目录
            dir.mkdirs();
        }
        // 获取扩展名

        String realExt = url.substring(url.lastIndexOf("."));

        if (!extSet.contains(realExt)) {
            realExt = ".jpg";
        }
        String fileName = name + realExt;

        fileName = fileName.replace("-", "");
        String filePath = path + fileName;
        File img = new File(filePath);
        if (img.exists()) {   // 若文件之前已经下载过，则跳过
            System.out.println(String.format("文件%s已存在本地目录", fileName));
            return;
        }
        URLConnection con = new URL(url).openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        InputStream inputStream = con.getInputStream();
        byte[] bs = new byte[1024];

        File file = new File(filePath);
        FileOutputStream os = new FileOutputStream(file, true);
        // 开始读取 写入
        int len;
        while ((len = inputStream.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        System.out.println("picUrl: " + url);
        System.out.println(String.format("正在下载第%s张图片", suc.getAndIncrement()));
    }

    public void process(String suff, List<String> data, String word) {
        long start = System.currentTimeMillis();
        int i = 0;
        for (String picUrl : data) {
            if (picUrl == null)
                continue;
            try {
                String name = "";
                if (i < 10) {
                    name = "000" + i;
                } else if (i < 100) {
                    name = "00" + i;
                } else if (i < 1000) {
                    name = "0" + i;
                }
                i++;
                downloadImg(picUrl, word, suff + "_" + name);
            } catch (Exception e) {
                e.printStackTrace();
                fails.incrementAndGet();
            }
        }
        System.out.println("下载成功: " + suc.get());
        System.out.println("下载失败: " + fails.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000 + "秒");

    }

    public void processSync(List<String> data, String word) {
        long start = System.currentTimeMillis();
        int count = 0;
        ExecutorService executorService = Executors.newCachedThreadPool(); // 创建缓存线程池
        for (int i = 0; i < data.size(); i++) {
            String picUrl = data.get(i);
            if (picUrl == null)
                continue;
            String name = "";
            if (i < 10) {
                name = "000" + i;
            } else if (i < 100) {
                name = "00" + i;
            } else if (i < 1000) {
                name = "0" + i;
            }
            String finalName = name;
            executorService.execute(() -> {
                try {
                    downloadImg(picUrl, word, finalName);
                } catch (Exception e) {
                    fails.incrementAndGet();
                }
            });
            count++;
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                // executorService.shutdownNow();
            }
            System.out.println("AwaitTermination Finished");
            System.out.println("共有URL: " + data.size());
            System.out.println("下载成功: " + suc);
            System.out.println("下载失败: " + fails);

            File dir = new File(this.path + "/" + word + "/");
            int len = Objects.requireNonNull(dir.list()).length;
            System.out.println("当前共有文件： " + len);

            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start) / 1000.0 + "秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void processSync2(List<String> data, final String word, int threadNum) {
        if (data.size() < threadNum) {
            process(null, data, word);
        } else {
            ExecutorService executorService = Executors.newCachedThreadPool();
            int num = data.size() / threadNum;    //每段要处理的数量
            for (int i = 0; i < threadNum; i++) {
                int start = i * num;
                int end = (i + 1) * num;
                if (i == threadNum - 1) {
                    end = data.size();
                }
                String suff = i + "";
                final List<String> cutList = data.subList(start, end);
                executorService.execute(() -> process(suff, cutList, word));
            }
            executorService.shutdown();
        }
    }

}
