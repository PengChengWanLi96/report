package com.fpj.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fpj.report.domain.ExaminationItem;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
public interface ExaminationItemService extends IService<ExaminationItem> {

    /**
     * 根据医院ID查询检查项目
     * @param hospitalId 医院ID
     * @return 检查项目列表
     */
    List<ExaminationItem> findByHospitalId(Long hospitalId);

    /**
     * 根据项目名称模糊查询
     * @param itemName 项目名称
     * @return 检查项目列表
     */
    List<ExaminationItem> findByItemNameLike(String itemName);

    /**
     * 根据检查类别查询
     * @param category 检查类别
     * @return 检查项目列表
     */
    List<ExaminationItem> findByCategory(String category);

    /**
     * 根据医院ID和类别查询检查项目
     * @param hospitalId 医院ID
     * @param category 检查类别
     * @return 检查项目列表
     */
    List<ExaminationItem> findByHospitalIdAndCategory(Long hospitalId, String category);
}