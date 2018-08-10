package hbi.demo.controllers;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * Created by 赵传昊 on 2018/8/7.
 */
public class MyJob extends AbstractJob{

    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
        JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        String msg = "赵传昊 + 20173";
        System.out.println(msg);
    }
}
