package hbi.demo.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.DemoOrder;

public interface IDemoOrderService extends IBaseService<DemoOrder>, ProxySelf<IDemoOrderService>{

}