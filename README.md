# 🎓 高校就业信息管理系统

一个现代化的高校就业信息管理平台，采用 **Spring Boot + Thymeleaf + MySQL + MongoDB** 技术栈，集成 **AI 智能就业助手**，帮助高校管理学生就业信息。

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-8.2-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

## ✨ 功能特性

### 📊 数据管理
- **学生信息管理** - 学号、姓名、专业、班级、联系方式等
- **就业信息管理** - 就业单位、职位、薪资、行业、省份等
- **院系/专业/行业管理** - 完整的基础数据维护

### 📈 数据可视化
- **首页仪表盘** - 统计总人数、就业率、平均薪资等核心指标
- **ECharts 图表** - 就业状态分布、行业分布、趋势分析

### 📁 Excel 导入导出
- **一键导出** - 导出学生/就业信息为 Excel 文件
- **批量导入** - 通过 Excel 批量导入学生数据

### 🤖 AI 智能助手
- **通义千问集成** - 基于 LangChain4j 的 AI 对话
- **就业指导** - 职业规划、简历撰写、面试技巧咨询
- **对话记忆** - MongoDB 存储对话历史，支持上下文理解

### 🔐 安全认证
- **Spring Security** - 安全的登录认证
- **用户注册** - 支持新用户自助注册
- **多管理员** - 支持多个管理员账号

## 🛠️ 技术栈

| 类别 | 技术 |
|------|------|
| 后端框架 | Spring Boot 3.2.0 |
| 模板引擎 | Thymeleaf |
| 数据库 | MySQL 8.0 + MongoDB |
| ORM | MyBatis |
| AI 集成 | LangChain4j + 通义千问 |
| Excel 处理 | EasyExcel |
| 安全框架 | Spring Security |
| 前端 | Bootstrap 5 + ECharts |

## 🚀 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- MongoDB 8.0+ (可选，用于 AI 对话记忆)

### 安装步骤

1. **克隆项目**
```bash
git clone https://github.com/jiuzheyangba24/college-employment-system.git
cd college-employment-system
```

2. **创建数据库**
```sql
CREATE DATABASE college CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. **配置数据库连接**
编辑 `src/main/resources/application.yaml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/college
    username: root
    password: 你的密码
```

4. **运行项目**
```bash
mvn spring-boot:run
```

5. **访问系统**
- 地址：http://localhost
- 默认账号：admin / 123456

## 📸 系统截图

### 登录页面
现代化 Glassmorphism 设计风格的登录界面

### 首页仪表盘
数据统计卡片 + ECharts 可视化图表

### AI 就业助手
智能对话，提供就业指导建议

## 📂 项目结构

```
src/main/java/com/student/app/
├── bean/           # 实体类
├── config/         # 配置类
├── controller/     # 控制器
├── dto/            # 数据传输对象
├── listener/       # Excel 监听器
├── mapper/         # MyBatis 接口
├── repository/     # MongoDB 仓库
└── service/        # 业务逻辑层

src/main/resources/
├── mapper/         # MyBatis XML 映射
├── static/         # 静态资源
├── templates/      # Thymeleaf 模板
└── application.yaml
```

## 👨‍💻 开发者

- **许海峰** - 软件工程233班

## 📄 License

MIT License - 详见 [LICENSE](LICENSE) 文件
