package com.student.app.mapper;

import com.student.app.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 学生数据访问接口
 */
@Mapper
public interface StudentMapper {
    
    /**
     * 查询所有学生
     */
    List<Student> findAll();
    
    /**
     * 分页查询学生
     */
    List<Student> findByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("keyword") String keyword);
    
    /**
     * 查询总数
     */
    int count(@Param("keyword") String keyword);
    
    /**
     * 根据ID查询学生
     */
    Student findById(@Param("id") Integer id);
    
    /**
     * 根据学号查询学生
     */
    Student findBySnum(@Param("snum") String snum);
    
    /**
     * 新增学生
     */
    int insert(Student student);
    
    /**
     * 更新学生
     */
    int update(Student student);
    
    /**
     * 删除学生
     */
    int deleteById(@Param("id") Integer id);
    
    /**
     * 批量插入学生
     */
    int batchInsert(@Param("list") List<Student> students);
    
    /**
     * 统计各状态学生数量
     */
    List<java.util.Map<String, Object>> countByStatus();
    
    /**
     * 统计各专业学生数量
     */
    List<java.util.Map<String, Object>> countByMajor();
}
