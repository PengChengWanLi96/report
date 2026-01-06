package com.fpj.report.controller;

import com.fpj.report.domain.Hospital;
import com.fpj.report.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 创建医院信息
     * @param hospital 医院信息
     * @return 创建后的医院信息
     */
    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        hospitalService.save(hospital);
        return hospital;
    }

    /**
     * 根据ID获取医院信息
     * @param id 医院ID
     * @return 医院信息
     */
    @GetMapping("/{id}")
    public Hospital getHospitalById(@PathVariable Long id) {
        return hospitalService.getById(id);
    }

    /**
     * 获取所有医院信息
     * @return 医院列表
     */
    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.list();
    }

    /**
     * 更新医院信息
     * @param id 医院ID
     * @param hospital 医院信息
     * @return 更新后的医院信息
     */
    @PutMapping("/{id}")
    public Hospital updateHospital(@PathVariable Long id, @RequestBody Hospital hospital) {
        hospital.setHospitalId(id);
        hospitalService.updateById(hospital);
        return hospital;
    }

    /**
     * 删除医院信息
     * @param id 医院ID
     */
    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id) {
        hospitalService.removeById(id);
    }

    /**
     * 根据名称模糊查询医院信息
     * @param keyword 查询关键字
     * @return 医院列表
     */
    @GetMapping("/search")
    public List<Hospital> searchHospitals(@RequestParam String keyword) {
        return hospitalService.findByHospitalNameLike(keyword);
    }
}

