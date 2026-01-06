package com.fpj.report.controller;

import com.fpj.report.domain.IndicatorItem;
import com.fpj.report.service.IndicatorItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@RestController
@RequestMapping("/api/indicator-items")
public class IndicatorItemController {

    @Autowired
    private IndicatorItemService indicatorItemService;

    /**
     * 创建指标项
     * @param item 指标项信息
     * @return 创建后的指标项
     */
    @PostMapping
    public IndicatorItem createIndicatorItem(@RequestBody IndicatorItem item) {
        indicatorItemService.save(item);
        return item;
    }

    /**
     * 根据ID获取指标项
     * @param id 指标项ID
     * @return 指标项信息
     */
    @GetMapping("/{id}")
    public IndicatorItem getIndicatorItemById(@PathVariable Long id) {
        return indicatorItemService.getById(id);
    }

    /**
     * 获取所有指标项
     * @return 指标项列表
     */
    @GetMapping
    public List<IndicatorItem> getAllIndicatorItems() {
        return indicatorItemService.list();
    }

    /**
     * 根据检查项目ID查询指标项
     * @param examinationItemId 检查项目ID
     * @return 指标项列表
     */
    @GetMapping("/examination/{examinationItemId}")
    public List<IndicatorItem> getIndicatorsByExaminationItem(@PathVariable Long examinationItemId) {
        return indicatorItemService.findByExaminationItemId(examinationItemId);
    }

    /**
     * 根据报告日期查询指标项
     * @param reportDate 报告日期
     * @return 指标项列表
     */
    @GetMapping("/date/{reportDate}")
    public List<IndicatorItem> getIndicatorsByReportDate(@PathVariable String reportDate) {
        return indicatorItemService.findByReportDate(LocalDate.parse(reportDate));
    }

    /**
     * 更新指标项
     * @param id 指标项ID
     * @param item 指标项信息
     * @return 更新后的指标项
     */
    @PutMapping("/{id}")
    public IndicatorItem updateIndicatorItem(@PathVariable Long id, @RequestBody IndicatorItem item) {
        item.setIndicatorId(id);
        indicatorItemService.updateById(item);
        return item;
    }

    /**
     * 删除指标项
     * @param id 指标项ID
     */
    @DeleteMapping("/{id}")
    public void deleteIndicatorItem(@PathVariable Long id) {
        indicatorItemService.removeById(id);
    }

    /**
     * 根据日期范围查询指标项
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 指标项列表
     */
    @GetMapping("/date-range")
    public List<IndicatorItem> getIndicatorsByDateRange(@RequestParam String startDate,
                                                        @RequestParam String endDate) {
        return indicatorItemService.findByReportDateBetween(
                LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}