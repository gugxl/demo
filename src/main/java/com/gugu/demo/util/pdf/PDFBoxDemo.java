package com.gugu.demo.util.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

/**
 * @author 小谷
 * @description
 * @since 2024/4/27
 */
public class PDFBoxDemo {
    public static void main(String[] args) throws Exception {
        // 读取原始 PDF 文件
        PDDocument document = PDDocument.load(new File("D:\\test.pdf"));
        // 遍历 PDF 中的所有页面
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            PDPage page = document.getPage(i);
            PDPageContentStream pdPageContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            // 设置字体和字号
            pdPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 99);
            // 设置透明度
            pdPageContentStream.setNonStrokingColor(200,200,200);
            // 添加文本水印
            pdPageContentStream.beginText();
            pdPageContentStream.newLineAtOffset(100, 100); // 设置水印位置
            pdPageContentStream.showText("小谷");
            pdPageContentStream.endText();
            pdPageContentStream.close();
        }
        document.save(new File("D:\\test11.pdf"));
        document.close();
    }
}
