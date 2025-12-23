package com.student.app.bean;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 行业实体类
 */
@Data
public class Industry {
    /** 行业ID */
    private Integer id;
    
    /** 行业名称 */
    private String name;
    
    /** 行业描述 */
    private String description;
    
    /** 创建时间 */
    private LocalDateTime createdAt;
    
    /** 更新时间 */
    private LocalDateTime updatedAt;
}
