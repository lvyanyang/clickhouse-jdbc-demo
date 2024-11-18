package com.example.clickhousejdbcdemo.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页结果
 * @author 吕艳阳
 */
@Getter
@Setter
public class PageList<E> {
    /**
     * 记录总数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    /**
     * 页码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageIndex;

    /**
     * 每页条数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;

    /**
     * 总页数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageCount;

    /**
     * 数据集合
     */
    private List<E> list;

    /**
     * 转换为PageList分页对象
     * @param list 原始分页集合
     */
    public static <E> PageList<E> of(List<E> list) {
        return PaginationHelper.of(list);
    }

    /**
     * 转换为PageList分页对象
     * @param list 分页集合
     */
    public static <E> PageList<E> ofPageList(Page<E> list) {
        return PaginationHelper.of(list);
    }

    /**
     * 转换为全部数据对象
     * @param list 全部数据集合
     */
    public static <E> PageList<E> ofAllList(List<E> list) {
        return PaginationHelper.ofAllList(list);
    }

    /**
     * 返回一个空分页对象
     */
    public static <E> PageList<E> empty() {
        return PaginationHelper.empty();
    }
}
