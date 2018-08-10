package hbi.demo.utils;

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
        System.out.println("赵传昊 + 20173");
    }
}
