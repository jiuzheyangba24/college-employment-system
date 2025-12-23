package com.student.app.service;

import com.student.app.bean.EmploymentInfo;
import java.util.List;
import java.util.Map;

/**
 * 就业信息业务逻辑接口
 */
public interface EmploymentInfoService {
    
    List<EmploymentInfo> findAll();
    
    Map<String, Object> findByPage(int page, int size, String keyword);
    
    EmploymentInfo findById(Integer id);
    
    EmploymentInfo findByStudentId(Integer studentId);
    
    void save(EmploymentInfo employmentInfo);
    
    void update(EmploymentInfo employmentInfo);
    
    void deleteById(Integer id);
    
    void batchInsert(List<EmploymentInfo> employmentInfoList);
    
    List<Map<String, Object>> countByProvince();
    
    List<Map<String, Object>> countByIndustry();
    
    List<Map<String, Object>> countByMonth();
    
    Double avgSalary();
}
