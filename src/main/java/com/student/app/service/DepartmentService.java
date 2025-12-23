package com.student.app.service;

import com.student.app.bean.Department;
import java.util.List;
import java.util.Map;

/**
 * 院系业务逻辑接口
 */
public interface DepartmentService {
    
    List<Department> findAll();
    
    Map<String, Object> findByPage(int page, int size, String keyword);
    
    Department findById(Integer id);
    
    void save(Department department);
    
    void update(Department department);
    
    void deleteById(Integer id);
}
