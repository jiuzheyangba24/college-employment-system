package com.student.app.service;

import com.student.app.bean.Industry;
import java.util.List;
import java.util.Map;

/**
 * 行业业务逻辑接口
 */
public interface IndustryService {
    
    List<Industry> findAll();
    
    Map<String, Object> findByPage(int page, int size, String keyword);
    
    Industry findById(Integer id);
    
    void save(Industry industry);
    
    void update(Industry industry);
    
    void deleteById(Integer id);
}
