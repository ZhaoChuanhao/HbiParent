package hbi.demo.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.demo.dto.DemoBankBranch;
import hbi.demo.service.IDemoBankBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.demo.dto.DemoBank;
import hbi.demo.service.IDemoBankService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoBankServiceImpl extends BaseServiceImpl<DemoBank> implements IDemoBankService{

   @Autowired
    private IDemoBankBranchService demoBankBranchService;
    @Autowired
    private IDemoBankService demoBankService;
    @Override
    public int myBatchDelect(IRequest request, List<DemoBank> list) {
        int deleteRowCount = 0;
        if(list == null || list.isEmpty() ){
            try {
                throw new Exception("缺失数据");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (DemoBank demoBank: list) {
            Long bankId = demoBank.getBankId();
            //查询头ID对应的行
            DemoBankBranch demoBankBranchSelect = new DemoBankBranch();
            demoBankBranchSelect.setBankId(bankId);
            List<DemoBankBranch> demoBankBranchList = demoBankBranchService.select(request, demoBankBranchSelect, 1, 0);
            if(demoBankBranchList != null || !demoBankBranchList.isEmpty()){
                //删除行
                demoBankBranchService.batchDelete(demoBankBranchList);
            }
            //统计删除的行数
            int i = demoBankService.deleteByPrimaryKey(demoBank);
            deleteRowCount += i;
        }
        return deleteRowCount;
    }

    @Override
    public List<DemoBank> myBatchUpdate(IRequest iRequest, List<DemoBank> demoBanks) {
        if (demoBanks != null && !demoBanks.isEmpty()) {
            for (int i = 0; i < demoBanks.size(); i++) {
                DemoBank demoBank = demoBanks.get(i);
                //根据是否拥bankId有判断是insert还是update
                Long bankId = demoBank.getBankId();
                if (bankId == null || bankId == 0) {
                    //insert
                    //保存头
                    demoBankService.insertSelective(iRequest, demoBank);
                    //insert之后便有主键了
                    bankId = demoBank.getBankId();
                    //保存行
                    List<DemoBankBranch> demoBankBranchList = demoBank.getDemoBankBranchList();
                    for (int j = 0; j < demoBankBranchList.size(); j++) {
                        DemoBankBranch demoBankBranch = demoBankBranchList.get(j);
                        demoBankBranch.setBankId(bankId);
                        demoBankBranchService.insertSelective(iRequest, demoBankBranch);
                    }

                } else {
                    //update
                    //保存头
                    demoBankService.updateByPrimaryKeySelective(iRequest, demoBank);
                    //保存行的时候需要区分行是新建还是更新
                    List<DemoBankBranch> demoBankBranchList = demoBank.getDemoBankBranchList();
                    if (demoBankBranchList != null && !demoBankBranchList.isEmpty()) {
                        for (int j = 0; j < demoBankBranchList.size(); j++) {
                            DemoBankBranch demoBankBranch = demoBankBranchList.get(j);
                            Long bankBranchId = demoBankBranch.getBankBranchId();
                            if (bankBranchId == null) {
                                demoBankBranch.setBankId(bankId);
                                demoBankBranchService.insertSelective(iRequest, demoBankBranch);
                            } else {
                                demoBankBranchService.updateByPrimaryKeySelective(iRequest, demoBankBranch);
                            }
                        }
                    }
                }
            }
        }
        return demoBanks;
    }
}