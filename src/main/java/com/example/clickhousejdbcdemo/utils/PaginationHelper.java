package com.example.clickhousejdbcdemo.utils;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页帮助类
 * @author 吕艳阳 lvyanyang@xci96716.com
 * @since 2023-05-14 12:00
 */
public class PaginationHelper {
    /**
     * 转换为PageList分页对象
     * @param list 原始分页集合
     */
    public static <E> PageList<E> of(List<E> list) {
        Page<E> plist = (Page<E>) list;
        PageList<E> pageResult = new PageList<>();
        pageResult.setList(plist.getResult());

        if (plist.getPageSize() > 0) {
            pageResult.setTotal(plist.getTotal());
            pageResult.setPageCount(plist.getPages());
            pageResult.setPageIndex(plist.getPageNum());
            pageResult.setPageSize(plist.getPageSize());
        } else {
            pageResult.setTotal((long) plist.size());
        }
        return pageResult;
    }

    /**
     * 转换为全部数据对象
     * @param list 全部数据集合
     */
    public static <E> PageList<E> ofAllList(List<E> list) {
        PageList<E> pageResult = new PageList<>();
        pageResult.setList(list);
        pageResult.setPageIndex(1);
        pageResult.setPageSize(list.size());
        pageResult.setPageCount(1);
        pageResult.setTotal((long) list.size());
        return pageResult;
    }

    /**
     * 返回一个空分页对象
     */
    public static <E> PageList<E> empty() {
        PageList<E> pageResult = new PageList<>();
        pageResult.setList(null);
        pageResult.setPageIndex(1);
        pageResult.setPageSize(10);
        pageResult.setPageCount(0);
        pageResult.setTotal(0L);
        return pageResult;
    }
}