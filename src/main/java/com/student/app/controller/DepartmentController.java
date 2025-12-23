package com.student.app.controller;

import com.student.app.bean.Department;
import com.student.app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 院系管理控制器
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
    
    /**
     * 院系列表页面
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments/list";
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    @ResponseBody
    public Map<String, Object> page(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String keyword) {
        return departmentService.findByPage(page, size, keyword);
    }
    
    /**
     * 获取所有院系（下拉框用）
     */
    @GetMapping("/all")
    @ResponseBody
    public java.util.List<Department> all() {
        return departmentService.findAll();
    }
    
    /**
     * 根据ID获取院系
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Department get(@PathVariable Integer id) {
        return departmentService.findById(id);
    }
    
    /**
     * 新增院系
     */
    @PostMapping
    @ResponseBody
    public Map<String, Object> save(@RequestBody Department department) {
        Map<String, Object> result = new HashMap<>();
        try {
            departmentService.save(department);
            result.put("success", true);
            result.put("message", "添加成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新院系
     */
    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody Department department) {
        Map<String, Object> result = new HashMap<>();
        try {
            department.setId(id);
            departmentService.update(department);
            result.put("success", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除院系
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            departmentService.deleteById(id);
            result.put("success", true);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
}
