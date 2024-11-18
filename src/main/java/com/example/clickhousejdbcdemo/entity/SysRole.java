/*
 * Copyright 2007-2024 西安交通信息投资营运有限公司 版权所有
 */

package com.example.clickhousejdbcdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 实体对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysRole {
    /**
     * 角色主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色分类
     */
    private String category;

    /**
     * 合计
     */
    private Double total;

    /**
     * 状态[1-启用, 0-禁用]
     */
    private Integer status;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String remark;
}