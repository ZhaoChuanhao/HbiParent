package hbi.demo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.demo.dto.OrgCompanys;
import hbi.demo.service.IOrgCompanysService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrgCompanysServiceImpl extends BaseServiceImpl<OrgCompanys> implements IOrgCompanysService{

}