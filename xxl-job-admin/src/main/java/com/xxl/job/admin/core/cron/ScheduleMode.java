package com.xxl.job.admin.core.cron;


/**
 * @ClassName ScheduleMode
 * @Description ScheduleMode
 * @Author wuxiaojian
 * @Date 2020/2/6 16:27
 * @Version 1.0
 **/

public enum ScheduleMode {
    CRON("00"),
    FIX("01");

    ScheduleMode(String mode){
        this.mode = mode;
    }

    private String mode;

    public String getMode() {
        return mode;
    }
}
