-- 创建数据库
CREATE DATABASE IF NOT EXISTS report
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

-- 创建医疗指标表
CREATE TABLE IF NOT EXISTS `medical_indicator` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `check_group` varchar(100) NOT NULL COMMENT '检查项目名称',
  `lab_indicator` varchar(100) NOT NULL COMMENT '检测指标名称',
  `unit` varchar(50) NOT NULL COMMENT '单位',
  `reference_range` varchar(50) DEFAULT NULL COMMENT '参考值范围',
  `reference_min` decimal(10,2) DEFAULT NULL COMMENT '参考值最小值',
  `reference_max` decimal(10,2) DEFAULT NULL COMMENT '参考值最大值',
  `result_value` decimal(10,2) NOT NULL COMMENT '检测结果值',
  `result_status` varchar(20) NOT NULL COMMENT '结果状态：偏高、偏低、正常',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标记：0-未删除，1-已删除',
  `check_date` date DEFAULT NULL COMMENT '检查日期',
    PRIMARY KEY (`id`),
  KEY `idx_check_group` (`check_group`),
  KEY `idx_lab_indicator` (`lab_indicator`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_result_status` (`result_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医疗检查指标表';

-- 医院信息表
CREATE TABLE `hospital` (
  `hospital_id` bigint NOT NULL AUTO_INCREMENT COMMENT '医院ID',
  `hospital_name` varchar(100) NOT NULL COMMENT '医院名称',
  `hospital_abbr` varchar(50) NOT NULL COMMENT '医院简称',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `level` varchar(20) DEFAULT NULL COMMENT '医院等级',
  `description` text COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`hospital_id`),
  UNIQUE KEY `uk_hospital_abbr` (`hospital_abbr`),
  KEY `idx_hospital_name` (`hospital_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医院信息表';

