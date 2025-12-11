package com.fpj.report.controller;


import com.fpj.report.common.ResultVO;
import com.fpj.report.service.MedicalIndicatorService;
import com.fpj.report.service.dto.MedicalIndicatorAddDTO;
import com.fpj.report.service.dto.MedicalIndicatorUpdateDTO;
import com.fpj.report.service.vo.MedicalIndicatorVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/medical_indicators")
@Tag(name = "医疗指标管理", description = "医疗检查指标数据的增删改查接口")
public class MedicalIndicatorController {

    @Autowired
    private MedicalIndicatorService medicalIndicatorService;

    @PostMapping
    @Operation(summary = "新增医疗指标", description = "创建新的医疗检查指标记录")
    public ResponseEntity<ResultVO<Long>> addMedicalIndicator(
            @Validated @RequestBody MedicalIndicatorAddDTO addDTO) {
        log.info("新增医疗指标接口被调用");

        boolean result = medicalIndicatorService.addMedicalIndicator(addDTO);
        if (result) {
            return ResponseEntity.ok(ResultVO.success("新增成功", null));
        } else {
            return ResponseEntity.badRequest().body(ResultVO.error("新增失败"));
        }
    }

    @PutMapping
    @Operation(summary = "修改医疗指标", description = "更新现有的医疗检查指标记录")
    public ResponseEntity<ResultVO<Void>> updateMedicalIndicator(
            @Validated @RequestBody MedicalIndicatorUpdateDTO updateDTO) {
        log.info("修改医疗指标接口被调用, ID: {}", updateDTO.getId());

        boolean result = medicalIndicatorService.updateMedicalIndicator(updateDTO);
        if (result) {
            return ResponseEntity.ok(ResultVO.success("修改成功", null));
        } else {
            return ResponseEntity.badRequest().body(ResultVO.error("修改失败或记录不存在"));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除医疗指标", description = "根据ID删除医疗检查指标记录")
    public ResponseEntity<ResultVO<Void>> deleteMedicalIndicator(@PathVariable Long id) {
        log.info("删除医疗指标接口被调用, ID: {}", id);

        boolean result = medicalIndicatorService.deleteMedicalIndicator(id);
        if (result) {
            return ResponseEntity.ok(ResultVO.success("删除成功", null));
        } else {
            return ResponseEntity.badRequest().body(ResultVO.error("删除失败或记录不存在"));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询指标详情", description = "根据ID获取医疗检查指标的详细信息")
    public ResponseEntity<ResultVO<MedicalIndicatorVO>> getMedicalIndicatorById(@PathVariable Long id) {
        log.info("查询医疗指标详情接口被调用, ID: {}", id);

        MedicalIndicatorVO indicatorVO = medicalIndicatorService.getMedicalIndicatorById(id);
        if (indicatorVO != null) {
            return ResponseEntity.ok(ResultVO.success("查询成功", indicatorVO));
        } else {
            return ResponseEntity.ok(ResultVO.error("记录不存在"));
        }
    }

    @GetMapping
    @Operation(summary = "查询指标列表", description = "获取所有医疗检查指标的列表")
    public ResponseEntity<ResultVO<List<MedicalIndicatorVO>>> getAllMedicalIndicators() {
        log.info("查询所有医疗指标列表接口被调用");

        List<MedicalIndicatorVO> indicators = medicalIndicatorService.getAllMedicalIndicators();
        return ResponseEntity.ok(ResultVO.success("查询成功", indicators));
    }

    @GetMapping("/check-group/{checkGroup}")
    @Operation(summary = "根据检查项目查询", description = "根据检查项目名称查询相关的医疗指标")
    public ResponseEntity<ResultVO<List<MedicalIndicatorVO>>> getMedicalIndicatorsByCheckGroup(
            @PathVariable String checkGroup) {
        log.info("根据检查项目查询指标列表接口被调用: {}", checkGroup);

        List<MedicalIndicatorVO> indicators = medicalIndicatorService.getMedicalIndicatorsByCheckGroup(checkGroup);
        return ResponseEntity.ok(ResultVO.success("查询成功", indicators));
    }

    @GetMapping("/health")
    @Operation(summary = "健康检查", description = "检查服务当前的健康状态")
    public ResultVO<Object> healthCheck() {
        log.info("健康检查接口被调用");

        return ResultVO.success("服务正常", null);
    }
}
