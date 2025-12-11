package com.fpj.report.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fpj.report.domain.MedicalIndicator;
import com.fpj.report.service.dto.MedicalIndicatorAddDTO;
import com.fpj.report.service.dto.MedicalIndicatorUpdateDTO;
import com.fpj.report.service.vo.MedicalIndicatorVO;
import java.util.List;

public interface MedicalIndicatorService  extends IService<MedicalIndicator> {

    /**
     * 新增医疗指标
     */
    boolean addMedicalIndicator(MedicalIndicatorAddDTO addDTO);

    /**
     * 修改医疗指标
     */
    boolean updateMedicalIndicator(MedicalIndicatorUpdateDTO updateDTO);

    /**
     * 删除医疗指标
     */
    boolean deleteMedicalIndicator(Long id);

    /**
     * 批量删除医疗指标
     */
    boolean batchDeleteMedicalIndicators(List<Long> ids);

    /**
     * 根据ID查询详情
     */
    MedicalIndicatorVO getMedicalIndicatorById(Long id);

    /**
     * 查询所有医疗指标列表
     */
    List<MedicalIndicatorVO> getAllMedicalIndicators();

    /**
     * 根据检查项目查询指标列表
     */
    List<MedicalIndicatorVO> getMedicalIndicatorsByCheckGroup(String checkGroup);

    /**
     * 根据指标名称模糊查询
     */
    List<MedicalIndicatorVO> getMedicalIndicatorsByLabIndicator(String labIndicator);

    /**
     * 根据结果状态查询
     */
    List<MedicalIndicatorVO> getMedicalIndicatorsByStatus(String resultStatus);
}
