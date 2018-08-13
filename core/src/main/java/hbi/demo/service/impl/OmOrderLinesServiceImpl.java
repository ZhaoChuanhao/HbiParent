package hbi.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.demo.mapper.OmOrderLinesMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.demo.dto.OmOrderLines;
import hbi.demo.service.IOmOrderLinesService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OmOrderLinesServiceImpl extends BaseServiceImpl<OmOrderLines> implements IOmOrderLinesService{

    @Autowired
    private OmOrderLinesMapper omOrderLinesMapper;

    @Override
    public Long getMaxLineNumber() {
        return omOrderLinesMapper.getMaxLineNumber();
    }

    @Override
    public List<OmOrderLines> selectOrderLines(IRequest request, OmOrderLines omOrderLines, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return omOrderLinesMapper.selectOrderLines(omOrderLines);
    }
}