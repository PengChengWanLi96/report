package com.fpj.report.domain;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("medical_indicator")
public class MedicalIndicator {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 检查项目名称
     */
    @TableField("check_group")
    private String checkGroup;

    /**
     * 指标名称
     */
    @TableField("lab_indicator")
    private String labIndicator;

    /**
     * 单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 参考值范围
     */
    @TableField("reference_range")
    private String referenceRange;

    /**
     * 参考值最小值
     */
    @TableField("reference_min")
    private Double referenceMin;

    /**
     * 参考值最大值
     */
    @TableField("reference_max")
    private Double referenceMax;

    /**
     * 检测结果
     */
    @TableField("result_value")
    private Double resultValue;

    /**
     * 结果状态：偏高、偏低、正常
     */
    @TableField("result_status")
    private String resultStatus;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField("deleted")
    private Integer deleted;

    /**
     * 检查日期
     */
    @TableField(value = "check_date")
    private LocalDate checkDate;
}
