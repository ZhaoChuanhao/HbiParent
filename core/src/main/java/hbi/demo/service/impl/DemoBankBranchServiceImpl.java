package hbi.demo.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.demo.dto.DemoBank;
import hbi.demo.service.IDemoBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.demo.dto.DemoBankBranch;
import hbi.demo.service.IDemoBankBranchService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoBankBranchServiceImpl extends BaseServiceImpl<DemoBankBranch> implements IDemoBankBranchService{
    @Autowired
    private IDemoBankService demoBankService;
    @Autowired
    private IDemoBankBranchService demoBankBranchService;
    @Override
    public List<DemoBankBranch> myBatchUpdate(IRequest iRequest, List<DemoBankBranch> demoBankBranches) {
        if(demoBankBranches != null && !demoBankBranches.isEmpty()){
            for (DemoBankBranch demoBankBranch :demoBankBranches) {
                Long bankBranchId = demoBankBranch.getBankBranchId();
                if(bankBranchId == null){
                    //新增
                    demoBankBranchService.insertSelective(iRequest,demoBankBranch);
                }else{
                    //更新
                    demoBankBranchService.updateByPrimaryKeySelective(iRequest,demoBankBranch);
                }
            }
        }
        return demoBankBranches;
    }
}