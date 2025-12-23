package com.student.app.controller;

import com.student.app.bean.Major;
import com.student.app.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专业管理控制器
 */
@Controller
@RequestMapping("/majors")
public class MajorController {
    
    @Autowired
    private MajorService majorService;
    
    /**
     * 专业列表页面
     */
    @GetMapping
    public String list(Model model) {
        return "majors/list";
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    @ResponseBody
    public Map<String, Object> page(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String keyword) {
        return majorService.findByPage(page, size, keyword);
    }
    
    /**
     * 获取所有专业（下拉框用）
     */
    @GetMapping("/all")
    @ResponseBody
    public List<Major> all() {
        return majorService.findAll();
    }
    
    /**
     * 根据院系ID获取专业列表
     */
    @GetMapping("/byDepartment/{departmentId}")
    @ResponseBody
    public List<Major> byDepartment(@PathVariable Integer departmentId) {
        return majorService.findByDepartmentId(departmentId);
    }
    
    /**
     * 根据ID获取专业
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Major get(@PathVariable Integer id) {
        return majorService.findById(id);
    }
    
    /**
     * 新增专业
     */
    @PostMapping
    @ResponseBody
    public Map<String, Object> save(@RequestBody Major major) {
        Map<String, Object> result = new HashMap<>();
        try {
            majorService.save(major);
            result.put("success", true);
            result.put("message", "添加成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新专业
     */
    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody Major major) {
        Map<String, Object> result = new HashMap<>();
        try {
            major.setId(id);
            majorService.update(major);
            result.put("success", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除专业
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            majorService.deleteById(id);
            result.put("success", true);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
}
