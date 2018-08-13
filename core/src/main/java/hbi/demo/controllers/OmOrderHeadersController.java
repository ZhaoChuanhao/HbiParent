package hbi.demo.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.demo.dto.OmOrderHeaders;
import hbi.demo.service.IOmOrderHeadersService;
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
    public class OmOrderHeadersController extends BaseController{

    @Autowired
    private IOmOrderHeadersService service;


    @RequestMapping(value = "/hap/om/order/headers/query")
    @ResponseBody
    public ResponseData query(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectOrderHeaders(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/hap/om/order/headers/selectExcelData")
    @ResponseBody
    public ResponseData selectExcelData(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectExcelData(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/hap/om/order/headers/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<OmOrderHeaders> dto, BindingResult result, HttpServletRequest request){
        //getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.myBatchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hap/om/order/headers/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<OmOrderHeaders> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/hap/om/order/headers/wholeDelete")
    @ResponseBody
    public ResponseData wholeDelete(HttpServletRequest request, OmOrderHeaders dto){
        IRequest requestCtx = createRequestContext(request);
        service.myBatchDelete(requestCtx, dto);
        return new ResponseData();
    }
    }