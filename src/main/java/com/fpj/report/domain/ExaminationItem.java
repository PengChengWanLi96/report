package com.fpj.report.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("examination_item")
public class ExaminationItem {

    @TableId(value = "item_id", type = IdType.AUTO)
    private Long itemId;

    @TableField(value = "item_name")
    private String itemName;

    @TableField(value = "hospital_id")
    private Long hospitalId;

    @TableField(value = "description")
    private String description;

    @TableField(value = "category")
    private String category;

    @TableField(value = "price")
    private Double price;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
}