package com.student.app.service.impl;

import com.student.app.bean.EmploymentInfo;
import com.student.app.mapper.EmploymentInfoMapper;
import com.student.app.service.EmploymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 就业信息业务逻辑实现
 */
@Service
public class EmploymentInfoServiceImpl implements EmploymentInfoService {
    
    @Autowired
    private EmploymentInfoMapper employmentInfoMapper;
    
    @Override
    public List<EmploymentInfo> findAll() {
        return employmentInfoMapper.findAll();
    }
    
    @Override
    public Map<String, Object> findByPage(int page, int size, String keyword) {
        int offset = (page - 1) * size;
        List<EmploymentInfo> list = employmentInfoMapper.findByPage(offset, size, keyword);
        int total = employmentInfoMapper.count(keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("pages", (int) Math.ceil((double) total / size));
        return result;
    }
    
    @Override
    public EmploymentInfo findById(Integer id) {
        return employmentInfoMapper.findById(id);
    }
    
    @Override
    public EmploymentInfo findByStudentId(Integer studentId) {
        return employmentInfoMapper.findByStudentId(studentId);
    }
    
    @Override
    public void save(EmploymentInfo employmentInfo) {
        employmentInfoMapper.insert(employmentInfo);
    }
    
    @Override
    public void update(EmploymentInfo employmentInfo) {
        employmentInfoMapper.update(employmentInfo);
    }
    
    @Override
    public void deleteById(Integer id) {
        employmentInfoMapper.deleteById(id);
    }
    
    @Override
    public void batchInsert(List<EmploymentInfo> employmentInfoList) {
        if (employmentInfoList != null && !employmentInfoList.isEmpty()) {
            employmentInfoMapper.batchInsert(employmentInfoList);
        }
    }
    
    @Override
    public List<Map<String, Object>> countByProvince() {
        return employmentInfoMapper.countByProvince();
    }
    
    @Override
    public List<Map<String, Object>> countByIndustry() {
        return employmentInfoMapper.countByIndustry();
    }
    
    @Override
    public List<Map<String, Object>> countByMonth() {
        return employmentInfoMapper.countByMonth();
    }
    
    @Override
    public Double avgSalary() {
        return employmentInfoMapper.avgSalary();
    }
}
