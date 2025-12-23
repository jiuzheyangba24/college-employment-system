package com.student.app.mapper;

import com.student.app.bean.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 专业数据访问接口
 */
@Mapper
public interface MajorMapper {
    
    /**
     * 查询所有专业
     */
    List<Major> findAll();
    
    /**
     * 分页查询专业
     */
    List<Major> findByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);
    
    /**
     * 查询总数
     */
    int count(@Param("keyword") String keyword);
    
    /**
     * 根据ID查询专业
     */
    Major findById(@Param("id") Integer id);
    
    /**
     * 根据院系ID查询专业列表
     */
    List<Major> findByDepartmentId(@Param("departmentId") Integer departmentId);
    
    /**
     * 新增专业
     */
    int insert(Major major);
    
    /**
     * 更新专业
     */
    int update(Major major);
    
    /**
     * 删除专业
     */
    int deleteById(@Param("id") Integer id);
}
