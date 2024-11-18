/*
 * Copyright 2007-2024 西安交通信息投资营运有限公司 版权所有
 */

package com.example.clickhousejdbcdemo.dao;

import com.example.clickhousejdbcdemo.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper操作接口
 */
public interface OperateDao {
    /** 插入 */
    int insert(@Param("entity") SysRole entity);

    /** 插入Map */
    int insertMap(@Param("map") Map<String, Object> map);

    /** 批量插入 */
    int insertBatch(@Param("list") List<SysRole> list);

    /** 查询单条记录 */
    SysRole selectById(@Param("id") Long id);

    /** 查询列表 */
    List<SysRole> selectList();

    /** 查询分页列表 */
    List<SysRole> selectPageList();

    /** 查询Map列表 */
    List<Map<String, Object>> selectMapList();
}