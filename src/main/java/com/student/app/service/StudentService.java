package com.student.app.service;

import com.student.app.bean.Student;
import java.util.List;
import java.util.Map;

/**
 * 学生业务逻辑接口
 */
public interface StudentService {
    
    List<Student> findAll();
    
    Map<String, Object> findByPage(int page, int size, String keyword);
    
    Student findById(Integer id);
    
    Student findBySnum(String snum);
    
    void save(Student student);
    
    void update(Student student);
    
    void deleteById(Integer id);
    
    void batchInsert(List<Student> students);
    
    List<Map<String, Object>> countByStatus();
    
    List<Map<String, Object>> countByMajor();
}
