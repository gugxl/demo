package com.gugu.demo.util.zip;

import lombok.SneakyThrows;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;

public class CompressTest {
    @SneakyThrows
    public static void main(String[] args) {
        // 创建压缩对象
        ZipArchiveEntry entry = new ZipArchiveEntry("Elasticsearch服务器开发.pdf");
        // 要压缩的文件
        File f = new File("d:\\Elasticsearch服务器开发（第2版）.pdf");
        FileInputStream fis = new FileInputStream(f);
        // 输出的对象 压缩的文件
        ZipArchiveOutputStream zipOutput = new ZipArchiveOutputStream(new File("d:\\Elasticsearch服务器开发.zip"));
        zipOutput.putArchiveEntry(entry);
        int i = 0, j;
        while ((j = fis.read()) != -1) {
            zipOutput.write(j);
            i++;
        }
        zipOutput.closeArchiveEntry();
        zipOutput.close();
        fis.close();

    }
}
