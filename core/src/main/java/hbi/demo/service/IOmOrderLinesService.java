package hbi.demo.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.OmOrderLines;

import java.util.List;

public interface IOmOrderLinesService extends IBaseService<OmOrderLines>, ProxySelf<IOmOrderLinesService>{

    /* *
     * 获取最大行号
     * @param
     * @return java.lang.Long
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/12
     */
    Long getMaxLineNumber();

    /* *
     * 获取订单详情
     * @param [request, omOrderLines, pageNum, pageSize]
     * @return java.util.List<hbi.demo.dto.OmOrderLines>
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/12
     */
    List<OmOrderLines> selectOrderLines(IRequest request, OmOrderLines omOrderLines, int pageNum, int pageSize);
}