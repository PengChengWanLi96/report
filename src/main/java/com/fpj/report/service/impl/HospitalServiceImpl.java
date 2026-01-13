package com.fpj.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fpj.report.domain.Hospital;
import com.fpj.report.repository.HospitalMapper;
import com.fpj.report.service.HospitalService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital>
        implements HospitalService {

    @Override
    public List<Hospital> findByHospitalNameLike(String hospitalName) {
        QueryWrapper<Hospital> wrapper = new QueryWrapper<>();
        wrapper.like("hospital_name", hospitalName);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Hospital findByHospitalAbbr(String hospitalAbbr) {
        QueryWrapper<Hospital> wrapper = new QueryWrapper<>();
        wrapper.eq("hospital_abbr", hospitalAbbr);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Hospital> findByAddressContaining(String address) {
        QueryWrapper<Hospital> wrapper = new QueryWrapper<>();
        wrapper.like("address", address);
        return baseMapper.selectList(wrapper);
    }
}

