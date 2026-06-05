<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.tpfh.fintech.modules.${prefix}.${function}.dao.${entityName}Dao">

    <!--表所有的列名集合-->
    <sql id="allColumns">
        <#list list as entity>
            ${entity.columnName}<#sep>,
        </#list>
    </sql>

    <!--除去主键id的所有列名集合-->
    <sql id="exceptIdColumns">
        <#list list as entity>
            <#if entity.columnExtra != "PRI">
                ${entity.columnName}<#sep>,
            </#if>
        </#list>
    </sql>
    
    <!-- 查询分页信息 -->
    <select id="get${entityName}ListForPage" resultType="com.tpfh.fintech.modules.${prefix}.${function}.entity.${entityName}Entity">
		 select <include refid="allColumns"/> from ${tableName}
		<where>
		<#list list as entity>
          	<if test="${entity.entityColumnName} != null and ${entity.entityColumnName} != ''"> and ${entity.columnName}=${r'#{'}${entity.entityColumnName}${r'}'}</if>
       	</#list>
		</where>
	</select>

    <!--查询符合条件的信息列表-->
    <select id="get${entityName}List" resultType="com.tpfh.fintech.modules.${prefix}.${function}.entity.${entityName}Entity">
        select <include refid="allColumns"/> from ${tableName}
        <where>
        <#list list as entity>
            <if test="${entity.entityColumnName} != null and ${entity.entityColumnName} != ''"> and ${entity.columnName}=${r'#{'}${entity.entityColumnName}${r'}'}</if>
        </#list>
        </where>
    </select>

	<!-- 新增实体信息 -->
    <insert id="add${entityName}" useGeneratedKeys="true" keyProperty="id" parameterType="java.util.HashMap">
        insert into ${tableName} (<include refid="exceptIdColumns"/>)
        values(
        <#list list as entity>
            <#if entity.columnExtra != "PRI">
                ${r'#{'}${entity.entityColumnName}${r'}'}<#sep>,
            </#if>
        </#list>
        );
    </insert>

    
</mapper>