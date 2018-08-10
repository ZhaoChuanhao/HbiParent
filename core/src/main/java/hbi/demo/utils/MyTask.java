package hbi.demo.utils;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import com.hand.hap.task.info.ExecutionInfo;
import com.hand.hap.task.info.TaskDataInfo;
import com.hand.hap.task.service.IExecuteListener;
import com.hand.hap.task.service.ITask;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 赵传昊 on 2018/8/7.
 */
public class MyTask implements ITask {

    @Autowired
    private ISysCodeRuleProcessService codeRuleProcessService;

    @Override
    public void execute(ExecutionInfo executionInfo) throws Exception {
        System.out.println("-------------");
        System.out.println("任务执行");
        //测试编码规范
        testMyCodeRule();
        System.out.println("-------------");

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("成绩表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        //在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("机试成绩");
        //在sheet里创建第三行
        HSSFRow row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("张三");
        row3.createCell(1).setCellValue("三班");
        row3.createCell(2).setCellValue(87);
        row3.createCell(3).setCellValue(78);

        //输出Excel文件
        FileOutputStream output = new FileOutputStream("D:\\test\\workbook.xls");
        wb.write(output);
        output.flush();
        //Map<String,Object> map =  executionInfo.getParam();
        //设置保存路径
        executionInfo.setExecuteResultPath("D:\\test\\workbook.xls");

    }

    void testMyCodeRule() throws CodeRuleException {
        System.out.println("======================");
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 10; i++){
            map.put("name","hand" + i);
            String hbi_demo_example = codeRuleProcessService.getRuleCode("HBI_DEMO_EXAMPLE", map);
            System.out.println(hbi_demo_example);
        }
        System.out.println("======================");
    }

}
