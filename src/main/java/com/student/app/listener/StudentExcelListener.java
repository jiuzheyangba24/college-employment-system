package com.student.app.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.student.app.bean.Major;
import com.student.app.bean.Student;
import com.student.app.dto.StudentExcelDTO;
import com.student.app.mapper.MajorMapper;
import com.student.app.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class StudentExcelListener implements ReadListener<StudentExcelDTO> {

    private final StudentService studentService;
    private final MajorMapper majorMapper;

    private List<Student> cachedDataList = ListUtils.newArrayListWithExpectedSize(100);
    private static final int BATCH_COUNT = 100;

    // 缓存专业名称到ID的映射，避免频繁查询数据库
    private Map<String, Integer> majorMap;

    public StudentExcelListener(StudentService studentService, MajorMapper majorMapper) {
        this.studentService = studentService;
        this.majorMapper = majorMapper;
        initMajorMap();
    }

    private void initMajorMap() {
        List<Major> majors = majorMapper.findAll(); // 假设MajorMapper有findAll方法
        this.majorMap = majors.stream().collect(Collectors.toMap(Major::getName, Major::getId));
    }

    @Override
    public void invoke(StudentExcelDTO data, AnalysisContext context) {
        Student student = new Student();
        student.setSnum(data.getSnum());
        student.setName(data.getName());
        student.setSex(data.getSex());
        student.setGrade(data.getGrade());
        student.setTel(data.getTel());
        student.setStatus(data.getStatus());

        // 处理专业关联
        if (data.getMajorName() != null && majorMap.containsKey(data.getMajorName())) {
            student.setMajorId(majorMap.get(data.getMajorName()));
        } else {
            // 如果专业不存在，可以设为null或者默认值，这里暂设为null
            log.warn("Unknown major name: {}", data.getMajorName());
        }

        cachedDataList.add(student);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("All data parsed and saved.");
    }

    private void saveData() {
        if (!cachedDataList.isEmpty()) {
            studentService.batchInsert(cachedDataList);
            log.info("{} students saved.", cachedDataList.size());
        }
    }
}
