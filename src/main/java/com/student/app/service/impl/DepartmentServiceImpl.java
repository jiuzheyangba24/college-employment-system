package com.student.app.service.impl;

import com.student.app.bean.Department;
import com.student.app.mapper.DepartmentMapper;
import com.student.app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 院系业务逻辑实现
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }
    
    @Override
    public Map<String, Object> findByPage(int page, int size, String keyword) {
        int offset = (page - 1) * size;
        List<Department> list = departmentMapper.findByPage(offset, size, keyword);
        int total = departmentMapper.count(keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("pages", (int) Math.ceil((double) total / size));
        return result;
    }
    
    @Override
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }
    
    @Override
    public void save(Department department) {
        departmentMapper.insert(department);
    }
    
    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }
    
    @Override
    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }
}
