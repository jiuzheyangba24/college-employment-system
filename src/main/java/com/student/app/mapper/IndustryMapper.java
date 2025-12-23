package com.student.app.mapper;

import com.student.app.bean.Industry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 行业数据访问接口
 */
@Mapper
public interface IndustryMapper {
    
    /**
     * 查询所有行业
     */
    List<Industry> findAll();
    
    /**
     * 分页查询行业
     */
    List<Industry> findByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);
    
    /**
     * 查询总数
     */
    int count(@Param("keyword") String keyword);
    
    /**
     * 根据ID查询行业
     */
    Industry findById(@Param("id") Integer id);
    
    /**
     * 新增行业
     */
    int insert(Industry industry);
    
    /**
     * 更新行业
     */
    int update(Industry industry);
    
    /**
     * 删除行业
     */
    int deleteById(@Param("id") Integer id);
}
