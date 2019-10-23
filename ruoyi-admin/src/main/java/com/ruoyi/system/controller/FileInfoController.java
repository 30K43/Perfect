package com.ruoyi.system.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.excel.ReadExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.FileInfo;
import com.ruoyi.system.service.IFileInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

/**
 * 文件信息Controller
 *
 * @author LH
 * @date 2019-10-13
 */
@Controller
@RequestMapping("/system/info")
public class FileInfoController extends BaseController {
    private String prefix = "system/info";

    @Autowired
    private IFileInfoService fileInfoService;

    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info() {
        return prefix + "/info";
    }

    /**
     * 查询文件信息列表
     */
    @RequiresPermissions("system:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FileInfo fileInfo) {
        startPage();
        List<FileInfo> list = fileInfoService.selectFileInfoList(fileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @RequiresPermissions("system:info:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FileInfo fileInfo) {
        List<FileInfo> list = fileInfoService.selectFileInfoList(fileInfo);
        ExcelUtil<FileInfo> util = new ExcelUtil<FileInfo>(FileInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增文件信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存文件信息
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, FileInfo fileInfo) throws IOException {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        fileInfo.setFilePath(fileName);
        return toAjax(fileInfoService.insertFileInfo(fileInfo));
    }

    /**
     * 修改文件信息
     */
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap) {
        FileInfo fileInfo = fileInfoService.selectFileInfoById(fileId);
        mmap.put("fileInfo", fileInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件信息
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FileInfo fileInfo) {
        return toAjax(fileInfoService.updateFileInfo(fileInfo));
    }

    /**
     * 删除文件信息
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(fileInfoService.deleteFileInfoByIds(ids));
    }

    /**
     * 自选商品 上传excel形式 更新商品sukId
     */

    @RequiresPermissions("integral:jdGoodpools:handleFile")
    @PostMapping("/uploader")
    @ResponseBody
    public List excelImport(@RequestParam(value = "file", required = false) MultipartFile file) {
        String extName = (file.getOriginalFilename()).substring((file.getOriginalFilename().lastIndexOf(".") + 1));
        //返回Map
        Map<String, Object> map = null;
        //存放导入数据
        ArrayList<Object> list = new ArrayList<>();
        //存放错误消息
        List<String> error = new ArrayList<>();
        FileInputStream fileInputStream;
        Workbook wb = null;
        try {
            fileInputStream = (FileInputStream) file.getInputStream();
            if ("xls".equals(extName)) {
                wb = new HSSFWorkbook(fileInputStream);
            }/* else {
                wb = new XSSFWorkbook(fileInputStream);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 第一页开始读取
        Sheet sheet = wb.getSheetAt(0);
        //第一行位置
        int firstRowIndex = sheet.getFirstRowNum() + 1;
        //最后一行位置
        int lastRowIndex = sheet.getLastRowNum();

        for (int rIndex = firstRowIndex; rIndex < lastRowIndex + 1; rIndex++) {
            Row row = sheet.getRow(rIndex);
            //第一列
            Cell cell1 = row.getCell(0);
            if (cell1 == null) {
                break;
            }
            cell1.setCellType(CellType.STRING);
            String idNumber = cell1.getStringCellValue().trim();
            if (StringUtils.isEmpty(idNumber)) {
                break;
            }
            //第二列
            Cell cell2 = row.getCell(1);
            if (cell2 == null) {
                break;
            }
            cell2.setCellType(CellType.STRING);
            String amount = cell2.getStringCellValue().trim();
            if (StringUtils.isEmpty(amount)) {
                break;
            }
            //第三列
            Cell cell3 = row.getCell(2);
            if (cell3 == null) {
                break;
            }
            cell3.setCellType(CellType.STRING);
            String describe = cell3.getStringCellValue().trim();
            if (StringUtils.isEmpty(describe)) {
                break;
            }
            //第四列
            Cell cell4 = row.getCell(3);
            if (cell4 == null) {
                break;
            }
            cell4.setCellType(CellType.STRING);
            String reqBy = cell4.getStringCellValue().trim();
            if (StringUtils.isEmpty(reqBy)) {
                break;
            }
            map = new HashMap();
            map.put("姓名", idNumber);
            map.put("年龄", amount);
            map.put("爱好", describe);
            map.put("金额", reqBy);
            list.add(map);

        }
        System.out.println(list);
        return list;

    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字转成string来读
        if (cell.getCellType() == CELL_TYPE_NUMERIC) {
            cell.setCellType(CELL_TYPE_STRING);
        }
//        判断数据类型
        switch (cell.getCellType()) {
            case CELL_TYPE_NUMERIC: //数字
                String.valueOf(cell.getNumericCellValue()).trim();
                break;
            case CELL_TYPE_STRING: //字符串
                String.valueOf(cell.getStringCellValue()).trim();
                break;
        }
        return cellValue;

    }

    public static Map<String, Object> readExcel(MultipartFile file, String extName) {
        //返回Map
        Map<String, Object> map = new HashMap<>();
        //存放导入数据
//        List<ProduceTemp> produceTempList = new ArrayList<>();
        //存放错误消息
        List<String> error = new ArrayList<>();
        //部门编号列表
//        List xyDeptCodeList = deptService.selectXyDeptCodeList();
        //创建输入流
        FileInputStream fileInputStream;
        Workbook wb = null;
        try {
            fileInputStream = (FileInputStream) file.getInputStream();
            if ("xls".equals(extName)) {
                wb = new HSSFWorkbook(fileInputStream);
            }/* else {
                wb = new XSSFWorkbook(fileInputStream);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 第一页开始读取
        Sheet sheet = wb.getSheetAt(0);
        //第一行位置
        int firstRowIndex = sheet.getFirstRowNum() + 1;
        //最后一行位置
        int lastRowIndex = sheet.getLastRowNum();

        int sum = 0;
        for (int rIndex = firstRowIndex; rIndex < lastRowIndex + 1; rIndex++) {
            Row row = sheet.getRow(rIndex);
//            ProduceTemp produceTemp = new ProduceTemp();

            //第一列   获取客户身份证号
            Cell cell1 = row.getCell(0);
            if (cell1 == null) {
                break;
            }
            cell1.setCellType(CellType.STRING);
            String idNumber = cell1.getStringCellValue().trim();
            if (StringUtils.isEmpty(idNumber)) {
                break;
            }


            //第二列   获取需要发放的积分数量
            Cell cell2 = row.getCell(1);
            if (cell2 == null) {
                break;
            }
            cell2.setCellType(CellType.STRING);
            String amount = cell2.getStringCellValue().trim();
            if (StringUtils.isEmpty(amount)) {
                break;
            }

            //第三列   获取发放原因
            Cell cell3 = row.getCell(2);
            if (cell3 == null) {
                break;
            }
            cell3.setCellType(CellType.STRING);
            String describe = cell3.getStringCellValue().trim();
            if (StringUtils.isEmpty(describe)) {
                break;
            }

            //第四列 机构代码
            Cell cell4 = row.getCell(3);
            if (cell4 == null) {
                break;
            }
            cell4.setCellType(CellType.STRING);
            String reqBy = cell4.getStringCellValue().trim();
            if (StringUtils.isEmpty(reqBy)) {
                break;
            }


            //校验身份证号码格式
//            if (!RegexUtils.checkIdCard(idNumber)) {
//                error.add("第" + (rIndex + 1) + "行，身份证号码格式不正确");
//            }
            //校验输入积分的格式
           /* if (!RegexUtils.isINTEGER_NEGATIVE(amount)) {
                error.add("第" + (rIndex + 1) + "行，积分数量格式不正确");
            }
            //校验发放原因
            if (StringUtils.isEmpty(describe)) {
                error.add("第" + (rIndex + 1) + "行,请填写发放事由");
            }
            //校验机构代码
            if (!xyDeptCodeList.contains(reqBy)) {
                error.add("第" + (rIndex + 1) + "行,请填写正确机构代码");
            }


            produceTemp.setReserve(idNumber);                                             //客户身份证号
            produceTemp.setAmount(Integer.parseInt(amount));                              //发放积分数量
            produceTemp.setDescribe(describe);                                            //存放发放原因
            produceTemp.setProvideId(produce.getProvideId());                             //批次ID  关联 文号
            produceTemp.setCreateId(produce.getCreateId());                               //池ID
            produceTemp.setCreateBy(produce.getCreateBy());                               //池名称
            produceTemp.setSendBy(ShiroUtils.getUserId().toString());                     //积分实际操作人ID|发放人ID
            produceTemp.setSendName(ShiroUtils.getSysUser().getUserName());               //积分实际操作人姓名|发放人姓名
            produceTemp.setReqBy(reqBy);                                                  //结算单位

            //计算发放积分总额
            sum += Integer.parseInt(amount);

            produceTempList.add(produceTemp);
            map.put("produceTempList", produceTempList);*/
          /*  map.put("error", error);
            map.put("sum", sum);*/
        }
        return map;

    }
}


