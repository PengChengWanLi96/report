package com.fpj.report.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "医疗指标新增DTO")
public class MedicalIndicatorAddDTO {

    @NotBlank(message = "检查项目不能为空")
    @Schema(description = "检查项目名称", example = "肝功能检查异常项")
    private String checkGroup;

    @NotBlank(message = "检测指标不能为空")
    @Schema(description = "检测指标名称", example = "总胆红素")
    private String labIndicator;

    @NotBlank(message = "单位不能为空")
    @Schema(description = "单位", example = "μmol/L")
    private String unit;

    @Schema(description = "参考值范围", example = "3-22")
    private String referenceRange;

    @NotNull(message = "参考值最小值不能为空")
    @Schema(description = "参考值最小值", example = "3.0")
    private Double referenceMin;

    @NotNull(message = "参考值最大值不能为空")
    @Schema(description = "参考值最大值", example = "22.0")
    private Double referenceMax;

    @NotNull(message = "检测结果不能为空")
    @Schema(description = "检测结果值", example = "23.1")
    private Double resultValue;

    @NotBlank(message = "结果状态不能为空")
    @Schema(description = "结果状态", example = "偏高")
    private String resultStatus;

    @Schema(description = "创建人", example = "admin")
    private String createdBy;
}
