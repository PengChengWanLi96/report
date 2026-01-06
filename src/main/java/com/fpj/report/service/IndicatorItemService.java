package com.fpj.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fpj.report.domain.IndicatorItem;
import java.time.LocalDate;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
public interface IndicatorItemService extends IService<IndicatorItem> {

    /**
     * 根据检查项目ID查询指标项
     * @param examinationItemId 检查项目ID
     * @return 指标项列表
     */
    List<IndicatorItem> findByExaminationItemId(Long examinationItemId);

    /**
     * 根据报告日期查询指标项
     * @param reportDate 报告日期
     * @return 指标项列表
     */
    List<IndicatorItem> findByReportDate(LocalDate reportDate);

    /**
     * 根据指标名称模糊查询
     * @param indicatorName 指标名称
     * @return 指标项列表
     */
    List<IndicatorItem> findByIndicatorNameLike(String indicatorName);

    /**
     * 根据日期范围查询指标项
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 指标项列表
     */
    List<IndicatorItem> findByReportDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * 根据检查项目ID和日期范围查询指标项
     * @param examinationItemId 检查项目ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 指标项列表
     */
    List<IndicatorItem> findByExaminationItemIdAndDateRange(Long examinationItemId, LocalDate startDate, LocalDate endDate);
}