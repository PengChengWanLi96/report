package com.fpj.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fpj.report.domain.IndicatorItem;
import com.fpj.report.repository.IndicatorItemMapper;
import com.fpj.report.service.IndicatorItemService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@Service
public class IndicatorItemServiceImpl extends ServiceImpl<IndicatorItemMapper, IndicatorItem>
        implements IndicatorItemService {

    @Override
    public List<IndicatorItem> findByExaminationItemId(Long examinationItemId) {
        QueryWrapper<IndicatorItem> wrapper = new QueryWrapper<>();
        wrapper.eq("examination_item_id", examinationItemId)
                .orderByDesc("report_date", "create_time");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<IndicatorItem> findByReportDate(LocalDate reportDate) {
        QueryWrapper<IndicatorItem> wrapper = new QueryWrapper<>();
        wrapper.eq("report_date", reportDate)
                .orderByDesc("create_time");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<IndicatorItem> findByIndicatorNameLike(String indicatorName) {
        QueryWrapper<IndicatorItem> wrapper = new QueryWrapper<>();
        wrapper.like("indicator_name", indicatorName)
                .orderByDesc("report_date", "create_time");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<IndicatorItem> findByReportDateBetween(LocalDate startDate, LocalDate endDate) {
        QueryWrapper<IndicatorItem> wrapper = new QueryWrapper<>();
        wrapper.between("report_date", startDate, endDate)
                .orderByDesc("report_date", "create_time");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<IndicatorItem> findByExaminationItemIdAndDateRange(Long examinationItemId, LocalDate startDate, LocalDate endDate) {
        QueryWrapper<IndicatorItem> wrapper = new QueryWrapper<>();
        wrapper.eq("examination_item_id", examinationItemId)
                .between("report_date", startDate, endDate)
                .orderByDesc("report_date", "create_time");
        return baseMapper.selectList(wrapper);
    }
}