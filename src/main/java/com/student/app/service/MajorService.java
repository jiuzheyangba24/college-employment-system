package com.student.app.service;

import com.student.app.bean.Major;
import java.util.List;
import java.util.Map;

/**
 * 专业业务逻辑接口
 */
public interface MajorService {
    
    List<Major> findAll();
    
    Map<String, Object> findByPage(int page, int size, String keyword);
    
    Major findById(Integer id);
    
    List<Major> findByDepartmentId(Integer departmentId);
    
    void save(Major major);
    
    void update(Major major);
    
    void deleteById(Integer id);
}
