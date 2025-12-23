package com.student.app.service.impl;

import com.student.app.bean.Student;
import com.student.app.mapper.StudentMapper;
import com.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生业务逻辑实现
 */
@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
    
    @Override
    public Map<String, Object> findByPage(int page, int size, String keyword) {
        int offset = (page - 1) * size;
        List<Student> list = studentMapper.findByPage(offset, size, keyword);
        int total = studentMapper.count(keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("pages", (int) Math.ceil((double) total / size));
        return result;
    }
    
    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }
    
    @Override
    public Student findBySnum(String snum) {
        return studentMapper.findBySnum(snum);
    }
    
    @Override
    public void save(Student student) {
        studentMapper.insert(student);
    }
    
    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }
    
    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }
    
    @Override
    public void batchInsert(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            studentMapper.batchInsert(students);
        }
    }
    
    @Override
    public List<Map<String, Object>> countByStatus() {
        return studentMapper.countByStatus();
    }
    
    @Override
    public List<Map<String, Object>> countByMajor() {
        return studentMapper.countByMajor();
    }
}
