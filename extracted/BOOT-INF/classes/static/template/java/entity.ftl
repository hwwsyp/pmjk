package com.tpfh.fintech.modules.${prefix}.${function}.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 货币实体
 * @author vhww
 * application.yml 中配置了
 * #驼峰下划线转换
 * mybatis-plus:global-config:db-column-underline: true
 * 所以库表在命名字段的名字时使用下划线  _ ，例如shortName 在库表中使用  short_name 命名
 */
@Data
@TableName("${tableName}")
public class ${entityName}Entity {
<#list list as entity>
   /**
    * ${entity.columnComment}
    */
<#if entity.columnExtra == "PRI">
    private Long ${entity.entityColumnName};
<#else>
   <#if entity.dataType == "varchar">
    private String ${entity.entityColumnName};
   <#elseif entity.dataType == "number">
    private Long ${entity.entityColumnName};
   <#elseif entity.dataType == "varchar2">
    private String ${entity.entityColumnName};
   <#elseif entity.dataType == "text">
    private String ${entity.entityColumnName};
   <#elseif entity.dataType == "datetime">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp ${entity.entityColumnName};
   <#elseif entity.dataType == "timestamp">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp ${entity.entityColumnName};
   <#elseif entity.dataType == "int">
    private Integer ${entity.entityColumnName};
   <#elseif entity.dataType == "tinyint">
    private Short ${entity.entityColumnName};
   <#elseif entity.dataType == "bigint">
    private Long ${entity.entityColumnName};
   <#elseif entity.dataType == "decimal">
    private BigDecimal ${entity.entityColumnName};
   <#else>
   </#if>
</#if>
</#list>
}