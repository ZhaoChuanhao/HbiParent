package hbi.demo.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.demo.dto.OmOrderHeaders;

import java.util.List;

public interface IOmOrderHeadersService extends IBaseService<OmOrderHeaders>, ProxySelf<IOmOrderHeadersService>{
    /* *
     * 查询订单头信息
     * @param  [request, omOrderHeaders, pageNum, pageSize]
     * @return  java.util.List<hbi.demo.dto.OmOrderHeaders>
     * @Author  赵传昊@hand-china.com
     * @Date  2018/8/11 12:49
     */
    List<OmOrderHeaders> selectOrderHeaders(IRequest request, OmOrderHeaders omOrderHeaders, int pageNum, int pageSize);

    /* *
     * 查询导出Excel所需要的数据
     * @param [request, omOrderHeaders, pageNum, pageSize]
     * @return java.util.List<hbi.demo.dto.OmOrderHeaders>
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/13
     */
    List<OmOrderHeaders> selectExcelData(IRequest request, OmOrderHeaders omOrderHeaders, int pageNum, int pageSize);

    /* *
     * 批量保存订单头和订单行
     * @param [request, omOrderHeadersList]
     * @return java.util.List<hbi.demo.dto.OmOrderHeaders>
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/12
     */
    List<OmOrderHeaders> myBatchUpdate(IRequest request, List<OmOrderHeaders> omOrderHeadersList);

    /* *
     * 整单删除，删除整个订单头和订单明细
     * @param [request, omOrderHeaders]
     * @return int
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/12
     */
    int myBatchDelete(IRequest request, OmOrderHeaders omOrderHeaders);
}