package com.student.app.controller;

import com.alibaba.excel.EasyExcel;
import com.student.app.bean.EmploymentInfo;
import com.student.app.bean.Student;
import com.student.app.dto.EmploymentExcelDTO;
import com.student.app.dto.StudentExcelDTO;
import com.student.app.listener.StudentExcelListener;
import com.student.app.mapper.MajorMapper;
import com.student.app.service.EmploymentInfoService;
import com.student.app.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.time.ZoneId;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmploymentInfoService employmentInfoService;

    @Autowired
    private MajorMapper majorMapper;

    /**
     * 导出学生数据
     */
    @GetMapping("/students/export")
    public void exportStudents(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("学生信息表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        List<Student> students = studentService.findAll();
        List<StudentExcelDTO> data = new ArrayList<>();

        for (Student student : students) {
            StudentExcelDTO dto = new StudentExcelDTO();
            // 简单复制属性，注意处理类型不一致
            dto.setSnum(student.getSnum());
            dto.setName(student.getName());
            dto.setSex(student.getSex());
            dto.setMajorName(student.getMajorName());
            dto.setGrade(student.getGrade());
            dto.setTel(student.getTel());
            dto.setStatus(student.getStatus());
            data.add(dto);
        }

        EasyExcel.write(response.getOutputStream(), StudentExcelDTO.class).sheet("学生信息").doWrite(data);
    }

    /**
     * 导入学生数据
     */
    @PostMapping("/students/import")
    public Map<String, Object> importStudents(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            EasyExcel.read(file.getInputStream(), StudentExcelDTO.class,
                    new StudentExcelListener(studentService, majorMapper)).sheet().doRead();
            return Map.of("success", true, "message", "导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("success", false, "message", "导入失败: " + e.getMessage());
        }
    }

    /**
     * 导出就业信息
     */
    @GetMapping("/employment/export")
    public void exportEmployment(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("就业信息表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        List<EmploymentInfo> list = employmentInfoService.findAll();
        List<EmploymentExcelDTO> data = new ArrayList<>();

        for (EmploymentInfo info : list) {
            EmploymentExcelDTO dto = new EmploymentExcelDTO();
            dto.setSnum(info.getStudentSnum());
            dto.setName(info.getStudentName());
            dto.setCompany(info.getCompany());
            dto.setPosition(info.getPosition());
            if (info.getSalary() != null) {
                dto.setSalary(BigDecimal.valueOf(info.getSalary()));
            }
            dto.setIndustryName(info.getIndustryName());
            dto.setProvince(info.getProvince());
            if (info.getEmploymentDate() != null) {
                dto.setEmploymentDate(
                        Date.from(info.getEmploymentDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            data.add(dto);
        }

        EasyExcel.write(response.getOutputStream(), EmploymentExcelDTO.class).sheet("就业信息").doWrite(data);
    }
}
