# 使用JDBC 并使用ClickHouseClient类库进行数据库的读写

官方文档:https://clickhouse.com/docs/en/integrations/java/jdbc-driver

# 测试表
```sql
CREATE TABLE IF NOT EXISTS sys_role
(
  id        	UInt64  					COMMENT '角色主键',
  name 			String 						COMMENT '角色名称',
  category  	LowCardinality(String) 	    COMMENT '角色分类',
  total     	Decimal(18, 2)  			COMMENT '合计',
  status 		Int8 	  DEFAULT 1 	    COMMENT '状态[1-启用, 0-禁用]',
  create_time   DateTime  DEFAULT now()	    COMMENT '创建日期',
  remark  		Nullable(String) 			COMMENT '备注'
)
ENGINE = MergeTree
ORDER BY (id,create_time)
SETTINGS index_granularity = 8192
COMMENT '系统角色';
```

