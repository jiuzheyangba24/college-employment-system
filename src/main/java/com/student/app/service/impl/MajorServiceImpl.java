package com.student.app.service.impl;

import com.student.app.bean.Major;
import com.student.app.mapper.MajorMapper;
import com.student.app.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专业业务逻辑实现
 */
@Service
public class MajorServiceImpl implements MajorService {
    
    @Autowired
    private MajorMapper majorMapper;
    
    @Override
    public List<Major> findAll() {
        return majorMapper.findAll();
    }
    
    @Override
    public Map<String, Object> findByPage(int page, int size, String keyword) {
        int offset = (page - 1) * size;
        List<Major> list = majorMapper.findByPage(offset, size, keyword);
        int total = majorMapper.count(keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("pages", (int) Math.ceil((double) total / size));
        return result;
    }
    
    @Override
    public Major findById(Integer id) {
        return majorMapper.findById(id);
    }
    
    @Override
    public List<Major> findByDepartmentId(Integer departmentId) {
        return majorMapper.findByDepartmentId(departmentId);
    }
    
    @Override
    public void save(Major major) {
        majorMapper.insert(major);
    }
    
    @Override
    public void update(Major major) {
        majorMapper.update(major);
    }
    
    @Override
    public void deleteById(Integer id) {
        majorMapper.deleteById(id);
    }
}
