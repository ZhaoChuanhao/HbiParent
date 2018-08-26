package hbi.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.demo.dto.OmOrderHeaders;
import hbi.demo.dto.OmOrderLines;
import hbi.demo.mapper.OmOrderHeadersMapper;
import hbi.demo.service.IOmOrderHeadersService;
import hbi.demo.service.IOmOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OmOrderHeadersServiceImpl extends BaseServiceImpl<OmOrderHeaders> implements IOmOrderHeadersService{

    @Autowired
    private IOmOrderHeadersService omOrderHeadersService;
    @Autowired
    private IOmOrderLinesService omOrderLinesService;
    @Autowired
    private OmOrderHeadersMapper omOrderHeadersMapper;
    @Autowired
    private ISysCodeRuleProcessService codeRuleProcessService;

    @Override
    public List<OmOrderHeaders> selectOrderHeaders(IRequest request, OmOrderHeaders omOrderHeaders, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return omOrderHeadersMapper.selectOrderHeaders(omOrderHeaders);
    }

    @Override
    public List<OmOrderHeaders> selectExcelData(IRequest request, OmOrderHeaders omOrderHeaders, int pageNum, int pageSize) {
        //PageHelper.startPage(pageNum, pageSize);
        return omOrderHeadersMapper.selectExcelData(omOrderHeaders);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OmOrderHeaders> myBatchUpdate(IRequest request, List<OmOrderHeaders> omOrderHeadersList) {
        if (omOrderHeadersList != null && !omOrderHeadersList.isEmpty()) {
            for (OmOrderHeaders omOrderHeaders: omOrderHeadersList) {

                //用编码规则生成销售订单号
                try {
                    String orderNumber = codeRuleProcessService.getRuleCode("HAP_RULE_DEMO_ORDER_NUMBER");
                    omOrderHeaders.setOrderNumber(orderNumber);
                } catch (CodeRuleException e) {
                    e.printStackTrace();
                }

                //根据是否有headerId判断是新建还是更新
                Long headerId = omOrderHeaders.getHeaderId();
                Long companyId = omOrderHeaders.getCompanyId();

                if (headerId == null) {
                    //新建
                    //保存头
                    omOrderHeadersService.insertSelective(request, omOrderHeaders);
                    //新建之后便有主键了
                    headerId = omOrderHeaders.getHeaderId();
                    companyId = omOrderHeaders.getCompanyId();

                    //保存行
                    List<OmOrderLines> omOrderLinesList = omOrderHeaders.getOmOrderLinesList();
                    //查询最大行号
                    /*Long maxLineNumber = omOrderLinesService.getMaxLineNumber();*/
                    //遍历集合，使每个OmOrderLines对象的行号加1,并绑定headerId
                    for (int i = 0; i < omOrderLinesList.size(); i++){
                        OmOrderLines omOrderLines = omOrderLinesList.get(i);
                        /*omOrderLines.setLineNumber(maxLineNumber + i + 1);*/
                        omOrderLines.setHeaderId(headerId);
                        omOrderLines.setCompanyId(companyId);
                        omOrderLinesService.insertSelective(request, omOrderLines);
                    }
                    omOrderHeadersService.updateByPrimaryKeySelective(request, omOrderHeaders);

                    //再重新查一遍订单信息（为了多表复合查询查出公司名称等）
                    omOrderHeadersList = omOrderHeadersMapper.selectOrderHeaders(omOrderHeaders);

                } else {
                    //更新
                    //保存头
                    omOrderHeadersService.updateByPrimaryKeySelective(request, omOrderHeaders);
                    //保存行的时候需要区分行是新建还是更新
                    List<OmOrderLines> omOrderLinesList = omOrderHeaders.getOmOrderLinesList();
                    if (omOrderLinesList != null && !omOrderLinesList.isEmpty()) {
                        for (int i = 0; i < omOrderLinesList.size(); i++) {
                            OmOrderLines omOrderLines = omOrderLinesList.get(i);
                            Long lineId = omOrderLines.getLineId();
                            //根据lineId是否为空，判断是新建还是更新
                            if (lineId == null) {
                                //新建
                                //查询最大行号
                                /*Long maxLineNumber = omOrderLinesService.getMaxLineNumber();*/
                                /*omOrderLines.setLineNumber(maxLineNumber + 1);*/
                                omOrderLines.setHeaderId(headerId);
                                omOrderLines.setCompanyId(companyId);
                                omOrderLinesService.insertSelective(request, omOrderLines);
                            } else {
                                omOrderLinesService.updateByPrimaryKeySelective(request, omOrderLines);
                            }
                        }
                        omOrderHeadersService.updateByPrimaryKeySelective(request, omOrderHeaders);
                    }
                }
            }
        }
        return omOrderHeadersList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OmOrderHeaders> updateOrderStatus(IRequest request, OmOrderHeaders omOrderHeaders) {
        List<OmOrderHeaders> omOrderHeadersList = new ArrayList<>();
        if(omOrderHeaders != null){
            String orderStatus = omOrderHeaders.getOrderStatus();
            //查询订单信息
            omOrderHeaders = omOrderHeadersService.selectByPrimaryKey(request, omOrderHeaders);
            //改变订单状态
            omOrderHeaders.setOrderStatus(orderStatus);
            omOrderHeadersService.updateByPrimaryKeySelective(request, omOrderHeaders);
            omOrderHeadersList.add(omOrderHeaders);
        }
        return omOrderHeadersList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int myBatchDelete(IRequest request, OmOrderHeaders omOrderHeaders) {
        int count = 0;
        if (omOrderHeaders != null) {
            Long headerId = omOrderHeaders.getHeaderId();
            if (headerId == null) {
                return count;
            }
            //查询对应行
            OmOrderLines omOrderLines = new OmOrderLines();
            omOrderLines.setHeaderId(headerId);
            List<OmOrderLines> omOrderLinesList = omOrderLinesService.select(request, omOrderLines, 1, 0);
            if (omOrderLinesList != null && !omOrderLinesList.isEmpty()) {
                //批量删除行
                omOrderLinesService.batchDelete(omOrderLinesList);
            }
            //删除头
            int n = omOrderHeadersService.deleteByPrimaryKey(omOrderHeaders);
            count += n;

        }
        return count;
    }
}