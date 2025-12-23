package com.student.app.controller;

import com.student.app.bean.Industry;
import com.student.app.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行业管理控制器
 */
@Controller
@RequestMapping("/industries")
public class IndustryController {
    
    @Autowired
    private IndustryService industryService;
    
    /**
     * 行业列表页面
     */
    @GetMapping
    public String list(Model model) {
        return "industries/list";
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    @ResponseBody
    public Map<String, Object> page(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String keyword) {
        return industryService.findByPage(page, size, keyword);
    }
    
    /**
     * 获取所有行业（下拉框用）
     */
    @GetMapping("/all")
    @ResponseBody
    public List<Industry> all() {
        return industryService.findAll();
    }
    
    /**
     * 根据ID获取行业
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Industry get(@PathVariable Integer id) {
        return industryService.findById(id);
    }
    
    /**
     * 新增行业
     */
    @PostMapping
    @ResponseBody
    public Map<String, Object> save(@RequestBody Industry industry) {
        Map<String, Object> result = new HashMap<>();
        try {
            industryService.save(industry);
            result.put("success", true);
            result.put("message", "添加成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新行业
     */
    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody Industry industry) {
        Map<String, Object> result = new HashMap<>();
        try {
            industry.setId(id);
            industryService.update(industry);
            result.put("success", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除行业
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            industryService.deleteById(id);
            result.put("success", true);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
}
