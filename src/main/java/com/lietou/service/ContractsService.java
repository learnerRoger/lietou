package com.lietou.service;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepoove.poi.XWPFTemplate;
import com.lietou.dto.Response;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.Contracts;
import com.lietou.mapper.ContractsMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Service
public class ContractsService extends ServiceImpl<ContractsMapper, Contracts> {


    public ResponseDTO add(Contracts contracts) {
        if (save(contracts)) {
            return Response.success();
        }
        return Response.error();
    }

    public ResponseDTO deleteById(Integer id) {
        if (removeById(id)) {
            return Response.success();
        }
        return Response.error();
    }

    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO edit(Contracts contracts) {
        if (updateById(contracts)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Contracts contracts = getById(id);
        if (contracts != null) {
            return Response.success(contracts);
        }
        return Response.error();
    }

    public ResponseDTO findAll() {
        List<Contracts> list = list();
        return Response.success(list);
    }

    public ResponseDTO list(Integer current, Integer size, Contracts contracts) {
        IPage<Contracts> config = new Page<>(current, size);
        QueryWrapper<Contracts> wrapper = new QueryWrapper<>();
        if (contracts.getConName() != "" && contracts.getConName() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("con_name", contracts.getConName());
        }
        if (contracts.getConCustomname() != "" && contracts.getConCustomname() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("con_customName", contracts.getConCustomname());
        }
        if (contracts.getConProjectname() != "" && contracts.getConProjectname() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("con_projectName", contracts.getConProjectname());
        }
        if (contracts.getConCompanycontractor() != "" && contracts.getConCompanycontractor() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("con_companyContractor", contracts.getConCompanycontractor());
        }
        if (contracts.getConCustomercontractor() != "" && contracts.getConCustomercontractor() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("con_customerContractor", contracts.getConCustomercontractor());
        }
        if (contracts.getConStatus() != "" && contracts.getConStatus() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("con_status", contracts.getConStatus());
        }
        IPage<Contracts> page = page(config, wrapper);
        // 将响应数据填充到map中
        Map map = new HashMap();
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return Response.success(map);
    }

    /**
     * 数据导出
     *
     * @param response
     * @return
     */
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<Contracts> list = list();
        // 通过工具类创建对象，可以指定磁盘路径
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 一次性将list内的数据写入到excel，使用默认样式,强制输出标题
        writer.write(list, true);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename = URLEncoder.encode("数据", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
        return Response.success();
    }

    /**
     * 数据导入
     *
     * @param file
     * @return
     */
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Contracts> list = reader.readAll(Contracts.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }

    public ResponseDTO down(@RequestBody Contracts contracts) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("/lietou/LieTouFile/contracts/template.docx")
                .render((new HashMap<String, Object>() {{
                    put("conCustomname",contracts.getConCustomname());
                    put("conAmount",contracts.getConAmount());
                    put("conCompanycontractor",contracts.getConCompanycontractor());
                    put("conCustomercontractor",contracts.getConCustomercontractor());
                    put("contractName", contracts.getConName());
                    put("conStarttime", contracts.getConStarttime());
                    put("conEndtime", contracts.getConEndtime());
                    put("conSignaddress", contracts.getConSignaddress());
                    put("conSigndate", contracts.getConSigndate());
                }}));
        template.writeAndClose(new FileOutputStream("D:\\LieTouFile\\contracts\\contractsDetail" + contracts.getConName() + ".docx"));
        return Response.success();
    }

}




