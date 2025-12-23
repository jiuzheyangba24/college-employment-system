package com.student.app.bean;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
public class User {
    /** 用户ID */
    private Integer id;
    
    /** 用户名 */
    private String username;
    
    /** 密码 */
    private String password;
    
    /** 昵称 */
    private String nickname;
    
    /** 联系电话 */
    private String tel;
    
    /** 头像 */
    private String avatar;
    
    /** 用户状态 */
    private String status;
    
    /** 创建时间 */
    private LocalDateTime createdAt;
    
    /** 更新时间 */
    private LocalDateTime updatedAt;
    
    /** 登录失败次数 */
    private Integer loginFailedCount;
    
    /** 最后登录失败时间 */
    private LocalDateTime lastFailedLoginTime;
    
    /** 账号是否锁定 */
    private String accountLocked;
}
