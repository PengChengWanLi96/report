package com.fpj.report.controller;

import com.fpj.report.domain.ExaminationItem;
import com.fpj.report.service.ExaminationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@RestController
@RequestMapping("/api/examination-items")
public class ExaminationItemController {

    @Autowired
    private ExaminationItemService examinationItemService;

    /**
     * 创建检查项目
     * @param item 检查项目信息
     * @return 创建后的检查项目
     */
    @PostMapping
    public ExaminationItem createExaminationItem(@RequestBody ExaminationItem item) {
        examinationItemService.save(item);
        return item;
    }

    /**
     * 根据ID获取检查项目
     * @param id 检查项目ID
     * @return 检查项目信息
     */
    @GetMapping("/{id}")
    public ExaminationItem getExaminationItemById(@PathVariable Long id) {
        return examinationItemService.getById(id);
    }

    /**
     * 获取所有检查项目
     * @return 检查项目列表
     */
    @GetMapping
    public List<ExaminationItem> getAllExaminationItems() {
        return examinationItemService.list();
    }

    /**
     * 根据医院ID查询检查项目
     * @param hospitalId 医院ID
     * @return 检查项目列表
     */
    @GetMapping("/hospital/{hospitalId}")
    public List<ExaminationItem> getItemsByHospital(@PathVariable Long hospitalId) {
        return examinationItemService.findByHospitalId(hospitalId);
    }

    /**
     * 更新检查项目
     * @param id 检查项目ID
     * @param item 检查项目信息
     * @return 更新后的检查项目
     */
    @PutMapping("/{id}")
    public ExaminationItem updateExaminationItem(@PathVariable Long id, @RequestBody ExaminationItem item) {
        item.setItemId(id);
        examinationItemService.updateById(item);
        return item;
    }

    /**
     * 删除检查项目
     * @param id 检查项目ID
     */
    @DeleteMapping("/{id}")
    public void deleteExaminationItem(@PathVariable Long id) {
        examinationItemService.removeById(id);
    }

    /**
     * 根据类别查询检查项目
     * @param category 检查类别
     * @return 检查项目列表
     */
    @GetMapping("/category/{category}")
    public List<ExaminationItem> getItemsByCategory(@PathVariable String category) {
        return examinationItemService.findByCategory(category);
    }
}