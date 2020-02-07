package com.xxl.job.admin.core.cron;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SimpleTrigger
 * @Description SimpleTrigger
 * @Author wuxiaojian
 * @Date 2020/2/2 16:27
 * @Version 1.0
 **/

public class SimpleTrigger {

    private volatile static Map<Integer, SimpleTrigger> triggerData = new ConcurrentHashMap<>();

    public volatile static Map<Integer, SimpleTrigger> fixTaskData = new ConcurrentHashMap<>();


    private int jobId;
    private int repeatCount;
    private long repeatInterval;

    private SimpleTrigger(Builder builder) {
        this.repeatCount = builder.repeatCount;
        this.repeatInterval = builder.repeatInterval;
        this.jobId = builder.jobId;
        if (triggerData.get(jobId) == null){
            triggerData.put(jobId, this);
        }
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public static class Builder {

        private int jobId;
        private int repeatCount;
        private long repeatInterval;

        public Builder withJobId(int jobId) {
            this.jobId = jobId;
            return this;
        }

        public Builder withRepeatCount(int repeatInterval) {
            this.repeatCount = repeatInterval;
            return this;
        }

        public Builder withIntervalInSeconds(long repeatInterval) {
            this.repeatInterval = repeatInterval;
            return this;
        }

        public SimpleTrigger builder() {
            return new SimpleTrigger(this);
        }
    }

    public Date getFireTimeAfter(Date afterTime) {
        if (afterTime == null) {
            afterTime = new Date();
        }

        if (triggerData.get(jobId) == null){
            return null;
        } else {
            repeatCount = triggerData.get(jobId).getRepeatCount();
        }

        if (repeatCount == 0) {
            return null;
        }

        long afterMillis = afterTime.getTime();


        Date time = new Date(afterMillis + repeatInterval * 1000);

        return time;
    }

    public static void updateRepeatCount(int jobId){
        SimpleTrigger trigger = triggerData.get(jobId);
        if (trigger != null){
            if (trigger.getRepeatCount() > 0){
                trigger.setRepeatCount(trigger.getRepeatCount()-1);
            }
            if (trigger.getRepeatCount() == 0){
                fixTaskData.put(jobId, trigger);
            }
        }
    }
}
