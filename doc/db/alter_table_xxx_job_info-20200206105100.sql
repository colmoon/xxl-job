ALTER TABLE `xxl_job_info`
MODIFY COLUMN `job_cron`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '任务执行CRON' AFTER `job_group`,
ADD COLUMN `job_trigger_mode`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 00 COMMENT '00：cron，01：fix' AFTER `job_group`,
ADD COLUMN `job_repeat_count`  int(11) NULL COMMENT '任务执行次数' AFTER `job_cron`,
ADD COLUMN `job_repeat_interval`  int(11) NULL COMMENT '任务执行间隔时间' AFTER `job_repeat_count`,
ADD COLUMN `job_delay`  int(11) DEFAULT 0 COMMENT '延时' AFTER `job_repeat_interval`;
