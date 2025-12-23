package com.student.app.mapper;

import com.student.app.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户数据访问接口
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     */
    List<User> findAll();

    /**
     * 根据用户名查询用户
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据ID查询用户
     */
    User findById(@Param("id") Integer id);

    /**
     * 新增用户
     */
    int insert(User user);

    /**
     * 更新用户
     */
    int update(User user);

    /**
     * 更新登录失败信息
     */
    int updateLoginFailed(@Param("id") Integer id, @Param("count") Integer count);

    /**
     * 重置登录失败次数
     */
    int resetLoginFailed(@Param("id") Integer id);

    /**
     * 更新密码
     */
    int updatePassword(@Param("id") Integer id, @Param("password") String password);
}
