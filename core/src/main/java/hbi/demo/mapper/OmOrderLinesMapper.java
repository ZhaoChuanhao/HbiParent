package hbi.demo.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.demo.dto.OmOrderLines;

import java.util.List;

public interface OmOrderLinesMapper extends Mapper<OmOrderLines>{
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
     * @param [omOrderLines]
     * @return java.util.List<hbi.demo.dto.OmOrderLines>
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/12
     */
    List<OmOrderLines> selectOrderLines(OmOrderLines omOrderLines);
}