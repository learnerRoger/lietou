package com.lietou.service;

import com.lietou.entity.Contracts;
import com.lietou.mapper.CustomerContactMapper;
import com.lietou.entity.CustomerContact;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lietou.dto.Response;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.Candidate;
import com.lietou.entity.Customer;
import com.lietou.mapper.CandidateMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 联系人信息表 服务类
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Service
public class CustomerContactService extends ServiceImpl<CustomerContactMapper, CustomerContact> {


    public ResponseDTO add(CustomerContact customerContact) {
        if (save(customerContact)) {
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


    public ResponseDTO edit(CustomerContact customerContact) {
        if (updateById(customerContact)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        CustomerContact customerContact = getById(id);
        if (customerContact != null) {
            return Response.success(customerContact);
        }
        return Response.error();
    }
    public ResponseDTO findAll() {
        List<CustomerContact> list = list();
        return Response.success(list);
    }

    public ResponseDTO list(Integer current, Integer size, String name) {
        IPage<CustomerContact> config = new Page<>(current, size);
        QueryWrapper<CustomerContact> wrapper = null;
        if (name != "" && name != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("name", name);
        }
        IPage<CustomerContact> page = page(config, wrapper);
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
        List<CustomerContact> list = list();
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
        List<CustomerContact> list = reader.readAll(CustomerContact.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }


}




