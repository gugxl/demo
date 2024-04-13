package com.gugu.demo.util.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gugu
 * @Classname ExcelExport
 * @Description TODO
 * @Date 2023/5/7 23:36
 */
public class ExcelExport {
    @SneakyThrows
    public static void main(String[] args) {
        writeExcelOneSheetOnceWrite();
    }

    /**
     * @throws IOException
     */
    public static void writeExcelOneSheetOnceWrite() throws IOException {
        // 查询数据导出即可 比如说一次性总共查询出100条数据
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            userList.add(new User(i+"", "name" + i, i, new Date()));
        }

        // 生成EXCEL并指定输出路径
        String fileName = "D:/test.xlsx"; // 文件路径
        EasyExcel.write(fileName, User.class)
            .sheet("用户数据") // 工作表名称
            .doWrite(userList); // 写入数据
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class User {
        @ExcelProperty("用户ID")
        private String userId;
        @ExcelProperty("名称")
        private String name;
        @ExcelProperty("年龄")
        private Integer age;
        @ExcelProperty("生日")
        private Date birthday;
    }

}
