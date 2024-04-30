package com.gugu.demo.util.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;

/**
 * @author 小谷
 * @description
 * @since 2024/4/28
 */
public class ItextWatermark {
    public static void main(String[] args) throws Exception {
        // 读取原始 PDF 文件
        PdfReader pdfReader = new PdfReader("D://test.pdf");
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("D://test_watermark.pdf"));
        // 获取 PDF 中的页数
        int pageCounts = pdfReader.getNumberOfPages();
        // 添加水印
        for (int i = 1; i <= pageCounts; i++) {
            PdfContentByte contentByte = pdfStamper.getOverContent(i);
            contentByte.beginText();
            contentByte.setFontAndSize(BaseFont.createFont(), 36);
            contentByte.setColorFill(BaseColor.GREEN);
            contentByte.showTextAligned(Element.ALIGN_CENTER, "Watermark", 300, 400, 45);
            contentByte.endText();
        }
        // 保存修改后的 PDF 文件并关闭文件流
        pdfStamper.close();
        pdfReader.close();
    }
}
