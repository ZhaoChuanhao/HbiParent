package hbi.demo.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.demo.dto.OmOrderHeaders;

import java.util.List;

public interface OmOrderHeadersMapper extends Mapper<OmOrderHeaders>{

    /**
     * 查询订单头信息
     * @param omOrderHeaders
     * @return java.util.List<hbi.demo.dto.OmOrderHeaders>
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/11
     */
    List<OmOrderHeaders> selectOrderHeaders(OmOrderHeaders omOrderHeaders);

    /* *
     * 查询导出Excel所需要的数据
     * @param [omOrderHeaders]
     * @return java.util.List<hbi.demo.dto.OmOrderHeaders>
     * @author chuanhao.zhao@hand-china.com
     * @date 2018/8/13
     */
    List<OmOrderHeaders> selectExcelData(OmOrderHeaders omOrderHeaders);
}