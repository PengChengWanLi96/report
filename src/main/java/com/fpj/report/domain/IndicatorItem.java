package com.fpj.report.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("indicator_item")
public class IndicatorItem {

    @TableId(value = "indicator_id", type = IdType.AUTO)
    private Long indicatorId;

    @TableField(value = "indicator_name")
    private String indicatorName;

    @TableField(value = "indicator_abbr")
    private String indicatorAbbr;

    @TableField(value = "examination_item_id")
    private Long examinationItemId;

    @TableField(value = "report_date")
    private LocalDate reportDate;

    @TableField(value = "description")
    private String description;

    @TableField(value = "unit")
    private String unit;

    @TableField(value = "reference_range")
    private String referenceRange;

    @TableField(value = "normal_min")
    private Double normalMin;

    @TableField(value = "normal_max")
    private Double normalMax;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
}