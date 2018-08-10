package hbi.demo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.demo.dto.DemoOrder;
import hbi.demo.service.IDemoOrderService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoOrderServiceImpl extends BaseServiceImpl<DemoOrder> implements IDemoOrderService{

}