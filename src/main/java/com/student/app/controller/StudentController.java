package com.student.app.controller;

import com.student.app.bean.Student;
import com.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生管理控制器
 */
@Controller
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    /**
     * 学生列表页面
     */
    @GetMapping
    public String list(Model model) {
        return "students/list";
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    @ResponseBody
    public Map<String, Object> page(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String keyword) {
        return studentService.findByPage(page, size, keyword);
    }
    
    /**
     * 获取所有学生
     */
    @GetMapping("/all")
    @ResponseBody
    public List<Student> all() {
        return studentService.findAll();
    }
    
    /**
     * 根据ID获取学生
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Student get(@PathVariable Integer id) {
        return studentService.findById(id);
    }
    
    /**
     * 新增学生
     */
    @PostMapping
    @ResponseBody
    public Map<String, Object> save(@RequestBody Student student) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 检查学号是否已存在
            if (studentService.findBySnum(student.getSnum()) != null) {
                result.put("success", false);
                result.put("message", "学号已存在");
                return result;
            }
            studentService.save(student);
            result.put("success", true);
            result.put("message", "添加成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新学生
     */
    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody Student student) {
        Map<String, Object> result = new HashMap<>();
        try {
            student.setId(id);
            studentService.update(student);
            result.put("success", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除学生
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            studentService.deleteById(id);
            result.put("success", true);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 统计接口
     */
    @GetMapping("/stats/status")
    @ResponseBody
    public List<Map<String, Object>> countByStatus() {
        return studentService.countByStatus();
    }
    
    @GetMapping("/stats/major")
    @ResponseBody
    public List<Map<String, Object>> countByMajor() {
        return studentService.countByMajor();
    }
}
