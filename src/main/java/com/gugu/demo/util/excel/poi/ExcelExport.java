package com.gugu.demo.util.excel.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
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
        File file = new File("D:\\excelTest.xlsx");

        createExcel(file);
        writeContent(file);
    }

    private static void writeContent(File file) {
        List<Student> list = new ArrayList<Student>();
        StringBuilder name = new StringBuilder("小红");
        StringBuilder address = new StringBuilder("杭州");

        for (int i = 0; i < 10; i++) {
            name = name.append(name);
            address = address.append(address);
        }

        Student student1 = new Student(name.toString(), false, 167, address.toString());
        Student student2 = new Student(name.toString(), false, 167, address.toString());

        list.add(student1);
        list.add(student2);

//        for (int i = 0; i < 19; i++) {
//            list.addAll(list);
//        }

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
@Data
@AllArgsConstructor
class Student {

    @Excel(name = "姓名")
    private String name;

    private boolean male;

    @Excel(name = "身高")
    private int height;

    @Excel(name = "地址")
    private String address;
}

