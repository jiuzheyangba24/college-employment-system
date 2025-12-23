package com.student.app.bean;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 就业信息实体类
 */
@Data
public class EmploymentInfo {
    /** 就业信息ID */
    private Integer id;
    
    /** 学生ID */
    private Integer studentId;
    
    /** 就业单位 */
    private String company;
    
    /** 职位 */
    private String position;
    
    /** 薪资 */
    private Double salary;
    
    /** 就业时间 */
    private LocalDate employmentDate;
    
    /** 行业ID */
    private Integer industryId;
    
    /** 就业省份 */
    private String province;
    
    /** 创建时间 */
    private LocalDateTime createdAt;
    
    /** 更新时间 */
    private LocalDateTime updatedAt;
    
    // 关联字段（非数据库字段）
    /** 学生姓名 */
    private String studentName;
    
    /** 学生学号 */
    private String studentSnum;
    
    /** 行业名称 */
    private String industryName;
}
