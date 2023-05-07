package com.gugu.demo.excel.poi;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gugu
 * @Classname ExcelExport
 * @Description TODO
 * @Date 2023/5/7 11:35
 */
public class ExcelExport {

    public static void main(String[] args) {
        File file = new File("D:\\applicationfiles\\tmp\\excelTest.xlsx");

        createExcel(file);
        writeContent(file);
    }

    private static void writeContent(File file) {
        List<Student> list = new ArrayList<Student>();
        Student student1 = new Student();
        student1.setName("小红");
        student1.setMale(false);
        student1.setHeight(167);

        Student student2 = new Student();
        student2.setName("小明");
        student2.setMale(true);
        student2.setHeight(185);

        list.add(student1);
        list.add(student2);

        ExcelUtil.createExcel(list, file);
    }

    @SneakyThrows
    private static void createExcel(File file) {
        XSSFWorkbook workbook = null;
        if (file.exists()) {
            throw new Exception("文件已经存在，请先移除，或者更换文件名！！！");
        } else {
            workbook = new XSSFWorkbook();
            // 如果文件不存在就创建一个工作簿，如果一个工作簿都没有的话 office是无法打开这个文件的。
            // 但是如果excel中已经存在工作簿直接创建时会覆盖已经存在的内容的
            Sheet sheet = workbook.createSheet("new sheet");
        }
    }
}

@Excel(name = "学生标签页")
class Student {

    @Excel(name = "姓名")
    private String name;

    private boolean male;

    @Excel(name = "身高")
    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

