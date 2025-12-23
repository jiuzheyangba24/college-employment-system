package com.student.app.bean;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 专业实体类
 */
@Data
public class Major {
    /** 专业ID */
    private Integer id;
    
    /** 专业名称 */
    private String name;
    
    /** 院系ID */
    private Integer departmentId;
    
    /** 学制 */
    private String duration;
    
    /** 专业简介 */
    private String description;
    
    /** 创建时间 */
    private LocalDateTime createdAt;
    
    /** 更新时间 */
    private LocalDateTime updatedAt;
    
    // 关联字段（非数据库字段）
    /** 院系名称 */
    private String departmentName;
}
