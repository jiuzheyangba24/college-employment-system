package com.student.app.bean;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 院系实体类
 */
@Data
public class Department {
    /** 院系ID */
    private Integer id;
    
    /** 院系名称 */
    private String name;
    
    /** 院长姓名 */
    private String dean;
    
    /** 联系电话 */
    private String phone;
    
    /** 院系简介 */
    private String description;
    
    /** 创建时间 */
    private LocalDateTime createdAt;
    
    /** 更新时间 */
    private LocalDateTime updatedAt;
}
