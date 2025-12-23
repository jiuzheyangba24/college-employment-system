package com.student.app.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmploymentExcelDTO {

    @ColumnWidth(20)
    @ExcelProperty("学号")
    private String snum;

    @ColumnWidth(15)
    @ExcelProperty("姓名")
    private String name;

    @ColumnWidth(25)
    @ExcelProperty("就业单位")
    private String company;

    @ColumnWidth(20)
    @ExcelProperty("职位")
    private String position;

    @ColumnWidth(15)
    @ExcelProperty("薪资")
    private BigDecimal salary;

    @ColumnWidth(15)
    @ExcelProperty("所属行业")
    private String industryName;

    @ColumnWidth(15)
    @ExcelProperty("就业省份")
    private String province;

    @ColumnWidth(20)
    @ExcelProperty("就业时间")
    private Date employmentDate;
}
