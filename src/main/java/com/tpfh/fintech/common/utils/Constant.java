/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.utils;

public class Constant {
    public static final Long SUPER_ADMIN = 1L;
    public static final String ACT_MUIT_LIST_NAME = "users";
    public static final String ACT_MUIT_VAR_NAME = "user";
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prd";
    public static final String SPRING_PROFILE_TEST = "uat";

    public static enum ActAction {
        APPROVE("1"),
        MULIT("2");

        private String value;

        private ActAction(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum ActBusType {
        ROOT("1"),
        GROUP("2"),
        BUS("3"),
        BACK("4");

        private String value;

        private ActBusType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum ActFlowDoView {
        SHOW_FLOW("1"),
        DO_TASK("2");

        private String value;

        private ActFlowDoView(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum ActResult {
        AGREE("1"),
        NO_AGREE("2"),
        DISAGREE("3");

        private String value;

        private ActResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum ActStauts {
        DRAFT("1"),
        APPROVAL("2"),
        END("3");

        private String value;

        private ActStauts(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum ActTaskResult {
        AGREE("1"),
        NO_AGREE("2"),
        ABSTAINED("3"),
        TURN_DOWN("4"),
        TURN_DO("5");

        private String value;

        private ActTaskResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum CloudService {
        QINIU(1),
        ALIYUN(2),
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum DataAuth {
        BA_DATA("1"),
        BAP_DATA("2"),
        ALL_DATA("3");

        private String value;

        private DataAuth(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum MenuType {
        CATALOG(0),
        MENU(1),
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum ScheduleStatus {
        NORMAL(0),
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum noticeType {
        UL_NOTICE("1"),
        ACT_NOTICE("2");

        private String value;

        private noticeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}

