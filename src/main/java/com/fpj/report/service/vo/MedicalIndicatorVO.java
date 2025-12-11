package com.fpj.report.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "医疗指标VO")
public class MedicalIndicatorVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "检查项目名称", example = "肝功能检查异常项")
    private String checkGroup;

    @Schema(description = "检测指标名称", example = "总胆红素")
    private String labIndicator;

    @Schema(description = "单位", example = "μmol/L")
    private String unit;

    @Schema(description = "参考值范围", example = "3-22")
    private String referenceRange;

    @Schema(description = "参考值最小值", example = "3.0")
    private Double referenceMin;

    @Schema(description = "参考值最大值", example = "22.0")
    private Double referenceMax;

    @Schema(description = "检测结果值", example = "23.1")
    private Double resultValue;

    @Schema(description = "结果状态", example = "偏高")
    private String resultStatus;

    @Schema(description = "创建人", example = "admin")
    private String createdBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人", example = "admin")
    private String updatedBy;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}