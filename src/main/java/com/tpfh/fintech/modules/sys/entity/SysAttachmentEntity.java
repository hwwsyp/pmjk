package com.tpfh.fintech.modules.sys.entity;

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
@TableName("sys_attachment")
public class SysAttachmentEntity {
   /**
    * 主键
    */
    private Long id;
   /**
    * 附件唯一编码
    */
    private String code;
   /**
    * 文件原名
    */
    private String srcFileName;
   /**
    * 系统文件名
    */
    private String destFileName;
   /**
    * 文件系统路径
    */
    private String filePath;
   /**
    * 文件访问路径
    */
    private String urlPath;
   /**
    * 上传时间
    */
    private String uploadTime;
   /**
    * 上传人
    */
    private String uploadUser;
}