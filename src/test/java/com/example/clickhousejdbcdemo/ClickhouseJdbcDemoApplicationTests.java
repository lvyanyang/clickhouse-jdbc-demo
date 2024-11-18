package com.example.clickhousejdbcdemo;

import com.clickhouse.client.internal.google.common.collect.Lists;
import com.example.clickhousejdbcdemo.dao.OperateDao;
import com.example.clickhousejdbcdemo.entity.SysRole;
import com.example.clickhousejdbcdemo.utils.JsonHelper;
import com.example.clickhousejdbcdemo.utils.PageList;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class ClickhouseJdbcDemoApplicationTests {
    @Resource private OperateDao operateDao;

    @Test
    void insert() {
        var entity = new SysRole(1501366508905959424L, "测试角色100", "临时", 100.12, 1, LocalDateTime.now().withNano(0), "测试备注");
        var affectedRows = operateDao.insert(entity);
        log.info("影响行数:{}", affectedRows);
    }

    @Test
    void insertMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1501366508905959425L);
        map.put("name", "测试角色200");
        map.put("category", "临时");
        map.put("total", 200.12);
        map.put("status", 0);
        map.put("createTime", LocalDateTime.now().withNano(0));
        map.put("remark", "测试备注");
        var affectedRows = operateDao.insertMap(map);
        log.info("影响行数:{}", affectedRows);
    }

    @Test
    void insertBatch() {
        log.info("开始构建集合");
        long startNanoTime = System.nanoTime();
        List<SysRole> list = new ArrayList<>();
        for (int i = 0; i < 1_0000; i++) {
            SysRole role = new SysRole();
            role.setId((i+1L));
            role.setName("批量名称" + i);
            role.setCategory("类别" + i);
            role.setTotal(1.0 + i);
            role.setStatus(1);
            role.setCreateTime(LocalDateTime.now().withNano(0));
            role.setRemark("备注" + i);
            list.add(role);
        }
        var elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanoTime);
        log.info("集合构建完成,size={},耗时:{}ms", list.size(), elapsedTime);

        log.info("开始分批写入数据库");
        startNanoTime = System.nanoTime();
        List<List<SysRole>> partition = Lists.partition(list, 500);
        for (List<SysRole> roles : partition) {
            operateDao.insertBatch(roles);
        }
        elapsedTime = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startNanoTime);
        log.info("批量写入数据库完成,size={},耗时:{}s", list.size(), elapsedTime);
    }

    @Test
    void selectById() {
        var entity = operateDao.selectById(1501366508905959424L);
        log.info(JsonHelper.toJsonString(entity));
    }

    @Test
    public void selectList() {
        var list = operateDao.selectList();
        list.forEach(p -> log.info(JsonHelper.toJsonString(p)));
    }

    @Test
    void selectPageList() {
        selectPageList(1);
        selectPageList(2);
    }

    void selectPageList(int pageIndex) {
        PageHelper.startPage(pageIndex, 10);
        var list = operateDao.selectPageList();
        var pageList = PageList.of(list);
        pageList.getList().forEach(p -> log.info(JsonHelper.toJsonString(p)));
        log.info("页数:{}  每页记录数:{}  总页数:{}  总记录数:{}", pageList.getPageIndex(), pageList.getPageSize(), pageList.getPageCount(), pageList.getTotal());
    }

    @Test
    void selectMapList() {
        var mapList = operateDao.selectMapList();
        mapList.forEach(p -> log.info(JsonHelper.toJsonString(p)));
    }
}
