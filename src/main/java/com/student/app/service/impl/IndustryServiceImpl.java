package com.student.app.service.impl;

import com.student.app.bean.Industry;
import com.student.app.mapper.IndustryMapper;
import com.student.app.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行业业务逻辑实现
 */
@Service
public class IndustryServiceImpl implements IndustryService {
    
    @Autowired
    private IndustryMapper industryMapper;
    
    @Override
    public List<Industry> findAll() {
        return industryMapper.findAll();
    }
    
    @Override
    public Map<String, Object> findByPage(int page, int size, String keyword) {
        int offset = (page - 1) * size;
        List<Industry> list = industryMapper.findByPage(offset, size, keyword);
        int total = industryMapper.count(keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("pages", (int) Math.ceil((double) total / size));
        return result;
    }
    
    @Override
    public Industry findById(Integer id) {
        return industryMapper.findById(id);
    }
    
    @Override
    public void save(Industry industry) {
        industryMapper.insert(industry);
    }
    
    @Override
    public void update(Industry industry) {
        industryMapper.update(industry);
    }
    
    @Override
    public void deleteById(Integer id) {
        industryMapper.deleteById(id);
    }
}
