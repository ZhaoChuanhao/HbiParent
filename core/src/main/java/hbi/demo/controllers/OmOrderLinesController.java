package hbi.demo.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.demo.dto.OmOrderLines;
import hbi.demo.service.IOmOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

    @Controller
    public class OmOrderLinesController extends BaseController{

    @Autowired
    private IOmOrderLinesService service;


    @RequestMapping(value = "/hap/om/order/lines/query")
    @ResponseBody
    public ResponseData query(OmOrderLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        if(dto.getHeaderId() == null || dto.getHeaderId() == 0){
            return new ResponseData(new ArrayList<>());
        }
        IRequest requestContext = createRequestContext(request);
        OmOrderLines dto2 = new OmOrderLines();
        dto2.setHeaderId(dto.getHeaderId());
        return new ResponseData(service.selectOrderLines(requestContext,dto2,page,pageSize));
    }

    @RequestMapping(value = "/hap/om/order/lines/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<OmOrderLines> dto, BindingResult result, HttpServletRequest request){
        //getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        //查询最大行号
        Long maxLineNumber = service.getMaxLineNumber();
        //遍历集合，使每个OmOrderLines对象的行号加1
        for (int i = 0; i < dto.size(); i++){
            OmOrderLines omOrderLines = dto.get(i);
            omOrderLines.setLineNumber(maxLineNumber + i + 1);
        }
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hap/om/order/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<OmOrderLines> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }