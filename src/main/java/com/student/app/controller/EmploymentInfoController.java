package com.student.app.controller;

import com.student.app.bean.EmploymentInfo;
import com.student.app.service.EmploymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 就业信息管理控制器
 */
@Controller
@RequestMapping("/employment")
public class EmploymentInfoController {
    
    @Autowired
    private EmploymentInfoService employmentInfoService;
    
    /**
     * 就业信息列表页面
     */
    @GetMapping
    public String list(Model model) {
        return "employment/list";
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    @ResponseBody
    public Map<String, Object> page(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String keyword) {
        return employmentInfoService.findByPage(page, size, keyword);
    }
    
    /**
     * 获取所有就业信息
     */
    @GetMapping("/all")
    @ResponseBody
    public List<EmploymentInfo> all() {
        return employmentInfoService.findAll();
    }
    
    /**
     * 根据ID获取就业信息
     */
    @GetMapping("/{id}")
    @ResponseBody
    public EmploymentInfo get(@PathVariable Integer id) {
        return employmentInfoService.findById(id);
    }
    
    /**
     * 新增就业信息
     */
    @PostMapping
    @ResponseBody
    public Map<String, Object> save(@RequestBody EmploymentInfo employmentInfo) {
        Map<String, Object> result = new HashMap<>();
        try {
            employmentInfoService.save(employmentInfo);
            result.put("success", true);
            result.put("message", "添加成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新就业信息
     */
    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody EmploymentInfo employmentInfo) {
        Map<String, Object> result = new HashMap<>();
        try {
            employmentInfo.setId(id);
            employmentInfoService.update(employmentInfo);
            result.put("success", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除就业信息
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            employmentInfoService.deleteById(id);
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
    @GetMapping("/stats/province")
    @ResponseBody
    public List<Map<String, Object>> countByProvince() {
        return employmentInfoService.countByProvince();
    }
    
    @GetMapping("/stats/industry")
    @ResponseBody
    public List<Map<String, Object>> countByIndustry() {
        return employmentInfoService.countByIndustry();
    }
    
    @GetMapping("/stats/month")
    @ResponseBody
    public List<Map<String, Object>> countByMonth() {
        return employmentInfoService.countByMonth();
    }
    
    @GetMapping("/stats/avgSalary")
    @ResponseBody
    public Map<String, Object> avgSalary() {
        Map<String, Object> result = new HashMap<>();
        result.put("avgSalary", employmentInfoService.avgSalary());
        return result;
    }
}
