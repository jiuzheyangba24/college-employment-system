package com.student.app.bean;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 学生实体类
 */
@Data
public class Student {
    /** 学生ID */
    private Integer id;
    
    /** 学号 */
    private String snum;
    
    /** 学生姓名 */
    private String name;
    
    /** 联系方式 */
    private String tel;
    
    /** 性别 */
    private String sex;
    
    /** 专业ID */
    private Integer majorId;
    
    /** 班级 */
    private String grade;
    
    /** 就业状态 */
    private String status;
    
    /** 创建时间 */
    private LocalDateTime createdAt;
    
    /** 更新时间 */
    private LocalDateTime updatedAt;
    
    // 关联字段（非数据库字段）
    /** 专业名称 */
    private String majorName;
    
    /** 院系名称 */
    private String departmentName;
}
