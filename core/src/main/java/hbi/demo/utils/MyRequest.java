package hbi.demo.utils;

import com.hand.hap.activiti.custom.IActivitiBean;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Created by 赵传昊 on 2018/8/7.
 */
@Component
public class MyRequest implements JavaDelegate,IActivitiBean {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("*****************");
        System.out.println("处理贷款请求");
        System.out.println("*****************");
    }

    @Override
    public String getBeanName() {
        return "processLoan";
    }
}
