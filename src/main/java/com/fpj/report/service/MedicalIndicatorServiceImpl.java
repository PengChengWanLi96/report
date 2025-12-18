package com.fpj.report.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fpj.report.domain.MedicalIndicator;
import com.fpj.report.repository.MedicalIndicatorMapper;
import com.fpj.report.service.dto.MedicalIndicatorAddDTO;
import com.fpj.report.service.dto.MedicalIndicatorQueryDTO;
import com.fpj.report.service.dto.MedicalIndicatorUpdateDTO;
import com.fpj.report.service.vo.MedicalIndicatorVO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class MedicalIndicatorServiceImpl extends ServiceImpl<MedicalIndicatorMapper, MedicalIndicator>
        implements MedicalIndicatorService {

    @Override
    public boolean addMedicalIndicator(MedicalIndicatorAddDTO addDTO) {
        log.info("新增医疗指标: {}", addDTO.getLabIndicator());

        try {
            MedicalIndicator indicator = new MedicalIndicator();
            BeanUtils.copyProperties(addDTO, indicator);

            // 设置参考值范围
            if (addDTO.getReferenceMin() != null && addDTO.getReferenceMax() != null) {
                indicator.setReferenceRange(addDTO.getReferenceMin() + "-" + addDTO.getReferenceMax());
            }

            // 设置创建和修改时间
            LocalDateTime now = LocalDateTime.now();
            indicator.setCreateTime(now);
            indicator.setUpdateTime(now);

            // 设置检查日期
            if (addDTO.getCheckDate() == null) {
                indicator.setCheckDate(LocalDate.now());
            }

            return this.save(indicator);
        } catch (Exception e) {
            log.error("新增医疗指标失败: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateMedicalIndicator(MedicalIndicatorUpdateDTO updateDTO) {
        log.info("修改医疗指标, ID: {}", updateDTO.getId());

        try {
            MedicalIndicator indicator = this.getById(updateDTO.getId());
            if (indicator == null) {
                log.error("医疗指标不存在, ID: {}", updateDTO.getId());
                return false;
            }

            BeanUtils.copyProperties(updateDTO, indicator);

            // 更新参考值范围
            if (updateDTO.getReferenceMin() != null && updateDTO.getReferenceMax() != null) {
                indicator.setReferenceRange(updateDTO.getReferenceMin() + "-" + updateDTO.getReferenceMax());
            }

            indicator.setUpdateTime(LocalDateTime.now());

            return this.updateById(indicator);
        } catch (Exception e) {
            log.error("修改医疗指标失败, ID: {}", updateDTO.getId(), e);
            return false;
        }
    }

    @Override
    public boolean deleteMedicalIndicator(Long id) {
        log.info("删除医疗指标, ID: {}", id);

        try {
            // 1. 构造要更新的字段
            MedicalIndicator entity = new MedicalIndicator();
            entity.setId(id);
            entity.setDeleted(1); // 或从配置中读取 logic-delete-value
            entity.setUpdateTime(LocalDateTime.now()); //  关键：手动设置更新时间

            // 2. 执行更新（代替 removeById）
            return this.updateById(entity);
        } catch (Exception e) {
            log.error("删除医疗指标失败, ID: {}", id, e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean batchDeleteMedicalIndicators(List<Long> ids) {
        log.info("批量删除医疗指标, IDs: {}", ids);

        if (CollectionUtils.isEmpty(ids)) {
            log.warn("批量删除的ID列表为空");
            return false;
        }
        try {
            ids.forEach(this::deleteMedicalIndicator);
            return this.removeByIds(ids);
        } catch (Exception e) {
            log.error("批量删除医疗指标失败", e);
            return false;
        }
    }

    @Override
    public MedicalIndicatorVO getMedicalIndicatorById(Long id) {
        log.info("查询医疗指标详情, ID: {}", id);

        try {
            MedicalIndicator indicator = this.getById(id);
            if (indicator == null) {
                return null;
            }

            MedicalIndicatorVO vo = new MedicalIndicatorVO();
            BeanUtils.copyProperties(indicator, vo);
            return vo;
        } catch (Exception e) {
            log.error("查询医疗指标详情失败, ID: {}", id, e);
            return null;
        }
    }

    @Override
    public List<MedicalIndicatorVO> getAllMedicalIndicators(MedicalIndicatorQueryDTO queryDTO) {
        log.info("查询所有医疗指标列表");

        try {
            LambdaQueryWrapper<MedicalIndicator> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(StringUtils.hasText(queryDTO.getCheckGroup()),
                    MedicalIndicator::getCheckGroup, queryDTO.getCheckGroup());
            queryWrapper.eq(StringUtils.hasText(queryDTO.getLabIndicator()),
                    MedicalIndicator::getLabIndicator, queryDTO.getLabIndicator());

            // MyBatis-Plus推荐的、类型安全的标准操作
            if (StringUtils.hasText(queryDTO.getLabIndicatorLike())) {
                queryWrapper.like(MedicalIndicator::getLabIndicator, queryDTO.getLabIndicatorLike().toLowerCase());
            }


//            //高度灵活的终极手段，用于处理标准API无法实现的复杂SQL逻辑
//            if (StringUtils.hasText(queryDTO.getLabIndicatorLike())) {
//                // 使用 apply 方法应用自定义的 SQL 条件片段
//                queryWrapper.apply("LOWER(lab_indicator) LIKE LOWER({0})",
//                        "%" + queryDTO.getLabIndicatorLike() + "%");
//            }

            if (queryDTO.getStartDate() != null && queryDTO.getEndDate() != null) {
                queryWrapper.ge(MedicalIndicator::getCheckDate, queryDTO.getStartDate())
                        .le(MedicalIndicator::getCheckDate, queryDTO.getEndDate());
            }

            queryWrapper.orderByDesc(MedicalIndicator::getCreateTime);

            List<MedicalIndicator> indicators = this.list(queryWrapper);
            return convertToVOList(indicators);
        } catch (Exception e) {
            log.error("查询所有医疗指标列表失败", e);
            return List.of();
        }
    }

    @Override
    public List<MedicalIndicatorVO> getMedicalIndicatorsByCheckGroup(String checkGroup) {
        log.info("根据检查项目查询指标列表: {}", checkGroup);

        if (!StringUtils.hasText(checkGroup)) {
            return List.of();
        }

        try {
            LambdaQueryWrapper<MedicalIndicator> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(MedicalIndicator::getCheckGroup, checkGroup)
                    .orderByDesc(MedicalIndicator::getCreateTime);

            List<MedicalIndicator> indicators = this.list(queryWrapper);
            return convertToVOList(indicators);
        } catch (Exception e) {
            log.error("根据检查项目查询指标列表失败: {}", checkGroup, e);
            return List.of();
        }
    }

    @Override
    public List<MedicalIndicatorVO> getMedicalIndicatorsByLabIndicator(String labIndicator) {
        log.info("根据指标名称模糊查询: {}", labIndicator);

        if (!StringUtils.hasText(labIndicator)) {
            return List.of();
        }

        try {
            LambdaQueryWrapper<MedicalIndicator> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(MedicalIndicator::getLabIndicator, labIndicator)
                    .orderByDesc(MedicalIndicator::getCreateTime);

            List<MedicalIndicator> indicators = this.list(queryWrapper);
            return convertToVOList(indicators);
        } catch (Exception e) {
            log.error("根据指标名称模糊查询失败: {}", labIndicator, e);
            return List.of();
        }
    }

    @Override
    public List<MedicalIndicatorVO> getMedicalIndicatorsByStatus(String resultStatus) {
        log.info("根据结果状态查询: {}", resultStatus);

        if (!StringUtils.hasText(resultStatus)) {
            return List.of();
        }

        try {
            LambdaQueryWrapper<MedicalIndicator> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(MedicalIndicator::getResultStatus, resultStatus)
                    .orderByDesc(MedicalIndicator::getCreateTime);

            List<MedicalIndicator> indicators = this.list(queryWrapper);
            return convertToVOList(indicators);
        } catch (Exception e) {
            log.error("根据结果状态查询失败: {}", resultStatus, e);
            return List.of();
        }
    }

    /**
     * 将实体列表转换为VO列表
     */
    private List<MedicalIndicatorVO> convertToVOList(List<MedicalIndicator> indicators) {
        if (CollectionUtils.isEmpty(indicators)) {
            return List.of();
        }

        return indicators.stream().map(indicator -> {
            MedicalIndicatorVO vo = new MedicalIndicatorVO();
            BeanUtils.copyProperties(indicator, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 使用Wrapper进行条件查询的示例方法
     */
    public List<MedicalIndicatorVO> getIndicatorsByCondition(String checkGroup, String resultStatus) {
        LambdaQueryWrapper<MedicalIndicator> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasText(checkGroup)) {
            queryWrapper.eq(MedicalIndicator::getCheckGroup, checkGroup);
        }

        if (StringUtils.hasText(resultStatus)) {
            queryWrapper.eq(MedicalIndicator::getResultStatus, resultStatus);
        }

        queryWrapper.orderByDesc(MedicalIndicator::getCreateTime);

        List<MedicalIndicator> indicators = this.list(queryWrapper);
        return convertToVOList(indicators);
    }

    /**
     * 批量更新示例方法
     */
    public boolean batchUpdateStatus(List<Long> ids, String newStatus) {
        if (CollectionUtils.isEmpty(ids) || !StringUtils.hasText(newStatus)) {
            return false;
        }

        try {
            LambdaUpdateWrapper<MedicalIndicator> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.in(MedicalIndicator::getId, ids)
                    .set(MedicalIndicator::getResultStatus, newStatus)
                    .set(MedicalIndicator::getUpdateTime, LocalDateTime.now());

            return this.update(updateWrapper);
        } catch (Exception e) {
            log.error("批量更新状态失败", e);
            return false;
        }
    }

    @Override
    public List<String> getGroupList(String group) {
        LambdaQueryWrapper<MedicalIndicator> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(group)) {
            queryWrapper.like(MedicalIndicator::getCheckGroup, group.toLowerCase());
        }
        List<MedicalIndicator> medicalIndicators = this.baseMapper.selectList(queryWrapper);
        return medicalIndicators.stream().map(MedicalIndicator::getCheckGroup).distinct().toList();
    }

    @Override
    public List<String> getIndicatorList(String group, String indicator) {
        LambdaQueryWrapper<MedicalIndicator> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(group)) {
            queryWrapper.like(MedicalIndicator::getCheckGroup, group.toLowerCase());
        }
        if (StringUtils.hasText(group)) {
            queryWrapper.like(MedicalIndicator::getCheckGroup, group.toLowerCase());
        }
        List<MedicalIndicator> medicalIndicators = this.baseMapper.selectList(queryWrapper);
        return medicalIndicators.stream().map(MedicalIndicator::getLabIndicator).distinct().toList();
    }
}