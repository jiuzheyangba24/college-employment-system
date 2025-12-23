package com.student.app.mapper;

import com.student.app.bean.EmploymentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 就业信息数据访问接口
 */
@Mapper
public interface EmploymentInfoMapper {
    
    /**
     * 查询所有就业信息
     */
    List<EmploymentInfo> findAll();
    
    /**
     * 分页查询就业信息
     */
    List<EmploymentInfo> findByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);
    
    /**
     * 查询总数
     */
    int count(@Param("keyword") String keyword);
    
    /**
     * 根据ID查询就业信息
     */
    EmploymentInfo findById(@Param("id") Integer id);
    
    /**
     * 根据学生ID查询就业信息
     */
    EmploymentInfo findByStudentId(@Param("studentId") Integer studentId);
    
    /**
     * 新增就业信息
     */
    int insert(EmploymentInfo employmentInfo);
    
    /**
     * 更新就业信息
     */
    int update(EmploymentInfo employmentInfo);
    
    /**
     * 删除就业信息
     */
    int deleteById(@Param("id") Integer id);
    
    /**
     * 批量插入就业信息
     */
    int batchInsert(@Param("list") List<EmploymentInfo> employmentInfoList);
    
    /**
     * 按省份统计就业分布
     */
    List<Map<String, Object>> countByProvince();
    
    /**
     * 按行业统计就业分布
     */
    List<Map<String, Object>> countByIndustry();
    
    /**
     * 按月份统计就业趋势
     */
    List<Map<String, Object>> countByMonth();
    
    /**
     * 统计平均薪资
     */
    Double avgSalary();
}
