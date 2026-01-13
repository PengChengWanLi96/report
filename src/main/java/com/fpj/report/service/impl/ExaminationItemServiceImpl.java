package com.fpj.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fpj.report.domain.ExaminationItem;
import com.fpj.report.repository.ExaminationItemMapper;
import com.fpj.report.service.ExaminationItemService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@Service
public class ExaminationItemServiceImpl extends ServiceImpl<ExaminationItemMapper, ExaminationItem>
        implements ExaminationItemService {

    @Override
    public List<ExaminationItem> findByHospitalId(Long hospitalId) {
        QueryWrapper<ExaminationItem> wrapper = new QueryWrapper<>();
        wrapper.eq("hospital_id", hospitalId)
                .orderByDesc("create_time");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<ExaminationItem> findByItemNameLike(String itemName) {
        QueryWrapper<ExaminationItem> wrapper = new QueryWrapper<>();
        wrapper.like("item_name", itemName)
                .orderByDesc("create_time");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<ExaminationItem> findByCategory(String category) {
        QueryWrapper<ExaminationItem> wrapper = new QueryWrapper<>();
        wrapper.eq("category", category)
                .orderByDesc("create_time");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<ExaminationItem> findByHospitalIdAndCategory(Long hospitalId, String category) {
        QueryWrapper<ExaminationItem> wrapper = new QueryWrapper<>();
        wrapper.eq("hospital_id", hospitalId)
                .eq("category", category)
                .orderByDesc("create_time");
        return baseMapper.selectList(wrapper);
    }
}