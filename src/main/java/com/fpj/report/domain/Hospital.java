package com.fpj.report.domain;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fangpengjun
 * @date 2026/1/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hospital")
public class Hospital {

    /*
    * 医院ID
     */
    @TableId(value = "hospital_id", type = IdType.AUTO)
    private Long hospitalId;

    /*
    * 医院名称
     */
    @TableField(value = "hospital_name")
    private String hospitalName;

    /*
    * 医院简称
     */
    @TableField(value = "hospital_abbr")
    private String hospitalAbbr;

    /*
    * 医院地址
     */
    @TableField(value = "address")
    private String address;

    /*
    * 医院描述
     */
    @TableField(value = "description")
    private String description;

    /*
    * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /*
    * 医院等级
     */
    @TableField(value = "level")
    private String level;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
}
