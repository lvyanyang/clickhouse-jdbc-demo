# 使用JDBC 并使用ClickHouseClient类库进行数据库的读写

官方文档:https://clickhouse.com/docs/en/integrations/java/jdbc-driver

# 测试表

```sql
create table if not exists sys_role
(
    id          UInt64 comment '角色主键',
    name        String comment '角色名称',
    category    LowCardinality(String) comment '角色分类',
    total       Decimal(18, 2) comment '合计',
    status      Int8     default 1 comment '状态[1-启用, 0-禁用]',
    create_time DateTime default now() comment '创建日期',
    remark      Nullable(String) comment '备注'
) engine = MergeTree order by (id, create_time) settings index_granularity = 8192 comment '系统角色';
```

# 关系型数据类型与ClickHouse数据类型对应关系

| 关系型数据类型                                             | ClickHouse数据类型                  |
|:----------------------------------------------------|:--------------------------------|
| TINYINT, BYTE                                       | Int8                            |
| SMALLINT                                            | Int16                           |
| INT, INTEGER                                        | Int32                           |
| BIGINT                                              | Int64                           |
| FLOAT                                               | Float32                         |
| DOUBLE                                              | Float64                         |
| Decimal,NUMBER                                      | Decimal                         |
| CHAR,NCHAR                                          | FixedString                     |
| VARCHAR,VARCHAR2,NVARCHAR2,TEXT,LONGTEXT,CLOB、NCLOB | String                          |
| DATE,DATETIME,TIMESTAMP                             | Date,Date32,DateTime,DateTime64 |
| BOOLEAN                                             | Boolean                         |
