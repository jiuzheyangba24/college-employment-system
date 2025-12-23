package com.student.app.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class StudentExcelDTO {

    @ColumnWidth(20)
    @ExcelProperty("学号")
    private String snum;

    @ColumnWidth(15)
    @ExcelProperty("姓名")
    private String name;

    @ColumnWidth(10)
    @ExcelProperty("性别")
    private String sex;

    @ColumnWidth(20)
    @ExcelProperty("专业")
    private String majorName;

    @ColumnWidth(15)
    @ExcelProperty("班级")
    private String grade;

    @ColumnWidth(20)
    @ExcelProperty("联系方式")
    private String tel;

    @ColumnWidth(15)
    @ExcelProperty("就业状态")
    private String status;
}
