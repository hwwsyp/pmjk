package com.tpfh.fintech.common.utils;

/**
 * 常量
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
	/** 超级管理员ID */
	public static final Long SUPER_ADMIN = 1L;

	 /**
     * 流程会签集合名称
     */
    public static final String ACT_MUIT_LIST_NAME="users";

    /**
     * 流程会签变量名称
     */
    public static final String ACT_MUIT_VAR_NAME="user";
    
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prd";
    public static final String SPRING_PROFILE_TEST = "uat";
	
	/**
	 * 菜单类型
	 * 
	 * @author tpfh
	 * @email tpfh@tpfh.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author tpfh
     * @email tpfh@tpfh.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 业务树类型
     */
    public enum ActBusType {
        /**
         * 根节点
         */
        ROOT("1"),
        /**
         * 分组
         */
        GROUP("2"),
        /**
         * 业务类
         */
        BUS("3"),
        /**
         * 回调
         */
        BACK("4");

        private String value;

        private ActBusType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 审批节点行为
     */
    public enum ActAction {
        /**
         * 审批
         */
        APPROVE("1"),
        /**
         * 会签
         */
        MULIT("2");

        private String value;

        private ActAction(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 流程状态
     */
    public enum ActStauts {
        /**
         * 草稿
         */
        DRAFT("1"),
        /**
         * 审批中
         */
        APPROVAL("2"),
        /**
         * 结束
         */
        END("3");

        private String value;

        private ActStauts(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 流程任务审批结果
     */
    public enum ActTaskResult {
        /**
         * 同意
         */
        AGREE("1"),
        /**
         * 反对
         */
        NO_AGREE("2"),
        /**
         * 弃权
         */
        ABSTAINED("3"),
        /**
         * 驳回
         */
        TURN_DOWN("4"),
        /**
         * 转办
         */
        TURN_DO("5");

        private String value;

        private ActTaskResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 整个流程的审批结果
     */
    public enum ActResult {
        /**
         * 同意
         */
        AGREE("1"),
        /**
         * 不同意
         */
        NO_AGREE("2"),
        /**
         * 审批中
         */
        DISAGREE("3");

        private String value;

        private ActResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 流程办理任务，和查询流程审批信息控件显示
     */
    public enum ActFlowDoView {
        /**
         * 查看审批过程
         */
        SHOW_FLOW("1"),
        /**
         * 办理任务
         */
        DO_TASK("2");

        private String value;

        private ActFlowDoView(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 数据权限
     */
    public enum DataAuth {
        /**
         * 部门数据权限
         */
        BA_DATA("1"),
        /**
         * 机构数据权限
         */
        BAP_DATA("2"),
        /**
         * 部门机构数据权限
         */
        ALL_DATA("3");

        private String value;

        private DataAuth(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 通知类型
     */
    public enum noticeType {
        /**
         *普通通知
         */
        UL_NOTICE("1"),
        /**
         * 流程通知
         */
        ACT_NOTICE("2");

        private String value;

        private noticeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
