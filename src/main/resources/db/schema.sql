-- 高校就业信息管理系统 - 数据库表结构

-- 创建数据库
CREATE DATABASE IF NOT EXISTS college CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE college;

-- 院系表
CREATE TABLE IF NOT EXISTS `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '院系ID',
  `name` varchar(30) NOT NULL COMMENT '院系名称',
  `dean` varchar(30) NOT NULL COMMENT '院长姓名',
  `phone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `description` text DEFAULT NULL COMMENT '院系简介',
  `created_at` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='院系表';

-- 专业表
CREATE TABLE IF NOT EXISTS `majors` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `name` varchar(30) NOT NULL COMMENT '专业名称',
  `department_id` int(11) NOT NULL COMMENT '院系外键',
  `duration` varchar(30) DEFAULT NULL COMMENT '学制',
  `description` text DEFAULT NULL COMMENT '专业简介',
  `created_at` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_major_department` (`department_id`),
  CONSTRAINT `fk_major_department` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专业表';

-- 行业表
CREATE TABLE IF NOT EXISTS `industries` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '行业ID',
  `name` varchar(30) DEFAULT NULL COMMENT '行业名称',
  `description` text DEFAULT NULL COMMENT '行业描述',
  `created_at` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行业表';

-- 学生表
CREATE TABLE IF NOT EXISTS `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `snum` varchar(30) NOT NULL COMMENT '学号',
  `name` varchar(30) NOT NULL COMMENT '学生姓名',
  `tel` varchar(30) DEFAULT NULL COMMENT '联系方式',
  `sex` enum('男','女') DEFAULT '男' COMMENT '性别',
  `major_id` int(11) NOT NULL COMMENT '专业外键',
  `grade` varchar(30) DEFAULT NULL COMMENT '班级',
  `status` varchar(20) DEFAULT '未就业' COMMENT '就业状态',
  `created_at` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `snum` (`snum`),
  KEY `fk_student_major` (`major_id`),
  CONSTRAINT `fk_student_major` FOREIGN KEY (`major_id`) REFERENCES `majors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 就业信息表
CREATE TABLE IF NOT EXISTS `employment_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '就业信息ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `company` varchar(30) NOT NULL COMMENT '就业单位',
  `position` varchar(30) DEFAULT NULL COMMENT '职位',
  `salary` double DEFAULT NULL COMMENT '薪资',
  `employment_date` date DEFAULT NULL COMMENT '就业时间',
  `industry_id` int(11) NOT NULL COMMENT '行业ID',
  `province` varchar(30) DEFAULT NULL COMMENT '就业省份',
  `created_at` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_employment_student` (`student_id`),
  KEY `fk_employment_industry` (`industry_id`),
  CONSTRAINT `fk_employment_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `fk_employment_industry` FOREIGN KEY (`industry_id`) REFERENCES `industries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='就业信息表';

-- 用户表
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` text NOT NULL COMMENT '密码',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `tel` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `avatar` text DEFAULT NULL COMMENT '头像',
  `status` enum('启用','禁用') DEFAULT '启用' COMMENT '用户状态',
  `created_at` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  `login_failed_count` int(11) DEFAULT 0 COMMENT '登录失败次数',
  `last_failed_login_time` datetime DEFAULT NULL COMMENT '最后登录失败时间',
  `account_locked` enum('是','否') DEFAULT '否' COMMENT '账号是否锁定',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
