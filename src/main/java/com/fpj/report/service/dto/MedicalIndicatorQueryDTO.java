package com.fpj.report.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

@Data
@Schema(description = "医疗指标查询DTO")
public class MedicalIndicatorQueryDTO {

    @Schema(description = "检查项目名称", example = "肝功能检查异常项")
    private String checkGroup;

    @Schema(description = "检测指标名称", example = "总胆红素")
    private String labIndicator;

    @Schema(description = "检测指标名称模糊查询", example = "总胆红素")
    private String labIndicatorLike;

    @Schema(description = "开始日期", example = "2025-12-01")
    private LocalDate startDate;

    @Schema(description = "结束日期", example = "2025-12-12")
    private LocalDate endDate;
}