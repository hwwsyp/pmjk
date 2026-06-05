<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.tpfh.fintech.modules.${prefix}.${function}.dao.${entityName}Dao">

    <!--表和实体映射关系-->
    <resultMap id="${entityName}ResultMap" type="com.tools.module.${prefix}.entity.${entityName}">
        <#list list as entity>
            <result column="${entity.columnName}" property="${entity.entityColumnName}"/>
        </#list>
    </resultMap>

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

    <!--查询符合条件的信息列表-->
    <select id="query" parameterType="com.tools.module.${prefix}.entity.${entityName}" resultMap="${entityName}ResultMap">
        select <include refid="allColumns"/> from ${tableName}
        <where>
        <#list list as entity>
            <if test="${entity.entityColumnName} != null and ${entity.entityColumnName} != ''"> and ${entity.columnName}=${r'#{'}${entity.entityColumnName}${r'}'}</if>
        </#list>
        </where>
    </select>

    <insert id="save" parameterType="com.tools.module.${prefix}.entity.${entityName}">
        insert into ${tableName} (<include refid="exceptIdColumns"/>)
        values(
        <#list list as entity>
            <#if entity.columnExtra != "PRI">
                ${r'#{'}${entity.entityColumnName}${r'}'}<#sep>,
            </#if>
        </#list>
        );
    </insert>

    <delete id="deleteById" parameterType="java.lang.Long">
        delete from ${tableName}
        where
        <#list list as entity>
            <#if entity.columnExtra == "PRI">
                ${entity.columnName} = ${r'#{'}${entity.entityColumnName}${r'}'}
            </#if>
        </#list>
    </delete>

    <update id="update" parameterType="com.tools.module.${prefix}.entity.${entityName}">
        update ${tableName}
        <set>
        <#list list as entity>
            <#if entity.columnExtra != "PRI">
                <if test="${entity.entityColumnName} != null and ${entity.entityColumnName} != ''">${entity.columnName}=${r'#{'}${entity.entityColumnName}${r'}'}<#sep>,</#sep></if>
            </#if>
        </#list>
        </set>
        where
        <#list list as entity>
            <#if entity.columnExtra == "PRI">
                ${entity.columnName} = ${r'#{'}${entity.entityColumnName}${r'}'}
            </#if>
        </#list>
    </update>

    <!--根据主键获取相应实体-->
    <select id="get${entityName}ById" parameterType="java.lang.Long" resultMap="${entityName}ResultMap">
        select <include refid="allColumns"/> from ${tableName}
        where
        <#list list as entity>
            <#if entity.columnExtra == "PRI">
                ${entity.columnName} = ${r'#{'}${entity.entityColumnName}${r'}'}
            </#if>
        </#list>
    </select>
</mapper>