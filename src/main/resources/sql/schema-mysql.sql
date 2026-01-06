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
CREATE TABLE IF NOT EXISTS `hospital` (
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


-- 检查项目表
CREATE TABLE IF NOT EXISTS `examination_item` (
  `item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '检查项目ID',
  `item_name` varchar(100) NOT NULL COMMENT '检查项目名称',
  `hospital_id` bigint NOT NULL COMMENT '医院ID',
  `description` text COMMENT '描述',
  `category` varchar(50) DEFAULT NULL COMMENT '检查类别',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`item_id`),
  KEY `idx_hospital_id` (`hospital_id`),
  KEY `idx_category` (`category`),
  KEY `idx_item_name` (`item_name`),
  CONSTRAINT `fk_examination_hospital` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='检查项目表';


-- 检查指标项表
CREATE TABLE IF NOT EXISTS `indicator_item` (
  `indicator_id` bigint NOT NULL AUTO_INCREMENT COMMENT '指标项ID',
  `indicator_name` varchar(100) NOT NULL COMMENT '指标项名称',
  `indicator_abbr` varchar(50) NOT NULL COMMENT '指标项简称',
  `examination_item_id` bigint NOT NULL COMMENT '检查项目ID',
  `report_date` date NOT NULL COMMENT '报告日期',
  `description` text COMMENT '描述',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `reference_range` varchar(100) DEFAULT NULL COMMENT '参考范围',
  `normal_min` decimal(10,2) DEFAULT NULL COMMENT '正常值下限',
  `normal_max` decimal(10,2) DEFAULT NULL COMMENT '正常值上限',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`indicator_id`),
  KEY `idx_examination_item_id` (`examination_item_id`),
  KEY `idx_report_date` (`report_date`),
  KEY `idx_indicator_name` (`indicator_name`),
  CONSTRAINT `fk_indicator_examination` FOREIGN KEY (`examination_item_id`) REFERENCES `examination_item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='检查指标项表';
