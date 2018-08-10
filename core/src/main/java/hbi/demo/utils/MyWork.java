package hbi.demo.utils;

import com.hand.hap.activiti.custom.IActivitiBean;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Created by 赵传昊 on 2018/8/7.
 */
@Component
public class MyWork implements JavaDelegate,IActivitiBean {
    @Override
    public void execute(DelegateExecution execution) {
        Integer amount = execution.getVariable("amount", Integer.class);
        Integer credit = execution.getVariable("credit", Integer.class);
        if(amount > 100000){
            if(credit > 80){
                execution.setVariable("accept",Boolean.TRUE);
            }else {
                execution.setVariable("accept",Boolean.FALSE);
            }
        }else{
            execution.setVariable("accept",Boolean.TRUE);
        }
    }

    @Override
    public String getBeanName() {
        return "checkCredit";
    }
}
