package hbi.demo.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.DemoBank;

import java.util.List;

public interface IDemoBankService extends IBaseService<DemoBank>, ProxySelf<IDemoBankService>{
    /* *
     * 批量删除方法
     * @author 武琦川@hand-china.com
     * @date  2018/8/4 15:22
     * @param request 请求
     * @param list 数据
     * @return 删除成功的条目数
     */
    int myBatchDelect(IRequest request, List<DemoBank> list);

    /* *
     * 保存投行和分行
     * @author 武琦川@hand-china.com
     * @date  2018/8/6 12:47
     * @param [iRequest, demoBanks]
     * @return java.util.List<hbi.demo.dto.DemoBank>
     */
    List<DemoBank> myBatchUpdate(IRequest iRequest, List<DemoBank> demoBanks);

}