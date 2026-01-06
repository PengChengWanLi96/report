package com.fpj.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fpj.report.domain.Hospital;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
public interface HospitalService extends IService<Hospital> {

    /**
     * 根据医院名称模糊查询
     * @param hospitalName 医院名称
     * @return 医院列表
     */
    List<Hospital> findByHospitalNameLike(String hospitalName);

    /**
     * 根据医院简称查询
     * @param hospitalAbbr 医院简称
     * @return 医院信息
     */
    Hospital findByHospitalAbbr(String hospitalAbbr);


    /**
     * 根据地址模糊查询
     * @param address 地址
     * @return 医院列表
     */
    List<Hospital> findByAddressContaining(String address);
}
