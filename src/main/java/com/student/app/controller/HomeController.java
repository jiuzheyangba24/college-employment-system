package com.student.app.controller;

import com.student.app.service.EmploymentInfoService;
import com.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 */
@Controller
public class HomeController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private EmploymentInfoService employmentInfoService;
    
    /**
     * 首页/仪表盘
     */
    @GetMapping("/")
    public String index(Model model) {
        // 学生状态统计
        List<Map<String, Object>> statusStats = studentService.countByStatus();
        model.addAttribute("statusStats", statusStats);
        
        // 计算总学生数和就业率
        int totalStudents = 0;
        int employedStudents = 0;
        for (Map<String, Object> stat : statusStats) {
            int value = ((Number) stat.get("value")).intValue();
            totalStudents += value;
            if ("已就业".equals(stat.get("name"))) {
                employedStudents = value;
            }
        }
        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("employmentRate", totalStudents > 0 ? 
            String.format("%.1f", (double) employedStudents / totalStudents * 100) : "0");
        
        // 平均薪资
        Double avgSalary = employmentInfoService.avgSalary();
        model.addAttribute("avgSalary", avgSalary != null ? String.format("%.0f", avgSalary) : "0");
        
        // 就业人数
        model.addAttribute("employedCount", employedStudents);
        
        return "index";
    }
    
    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
