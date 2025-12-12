package com.fpj.report.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
@Schema(description = "医疗指标修改DTO")
public class MedicalIndicatorUpdateDTO {

    @NotNull(message = "ID不能为空")
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

    @Schema(description = "修改人", example = "admin")
    private String updatedBy;

    @Schema(description = "检查日期", example = "2025-12-12")
    private LocalDate checkDate;
}