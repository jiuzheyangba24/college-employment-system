package com.student.app.mapper;

import com.student.app.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 院系数据访问接口
 */
@Mapper
public interface DepartmentMapper {
    
    /**
     * 查询所有院系
     */
    List<Department> findAll();
    
    /**
     * 分页查询院系
     */
    List<Department> findByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);
    
    /**
     * 查询总数
     */
    int count(@Param("keyword") String keyword);
    
    /**
     * 根据ID查询院系
     */
    Department findById(@Param("id") Integer id);
    
    /**
     * 新增院系
     */
    int insert(Department department);
    
    /**
     * 更新院系
     */
    int update(Department department);
    
    /**
     * 删除院系
     */
    int deleteById(@Param("id") Integer id);
}
