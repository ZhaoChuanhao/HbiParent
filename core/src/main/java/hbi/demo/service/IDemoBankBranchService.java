package hbi.demo.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.DemoBank;
import hbi.demo.dto.DemoBankBranch;

import java.util.List;

public interface IDemoBankBranchService extends IBaseService<DemoBankBranch>, ProxySelf<IDemoBankBranchService>{
    /* *
     * 保存投行和分行
     * @author 武琦川@hand-china.com
     * @date  2018/8/6 12:54
     * @param [iRequest, demoBankBranches]
     * @return java.util.List<hbi.demo.dto.DemoBank>
     */
    List<DemoBankBranch> myBatchUpdate(IRequest iRequest, List<DemoBankBranch> demoBankBranches);

}