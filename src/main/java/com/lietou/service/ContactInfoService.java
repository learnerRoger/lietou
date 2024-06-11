package com.lietou.service;

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
import com.lietou.entity.ContactInfo;
import com.lietou.mapper.ContactInfoMapper;
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

/**
 * <p>
 * 联系信息表 服务类
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Service
public class ContactInfoService extends ServiceImpl<ContactInfoMapper, ContactInfo> {


    public ResponseDTO add(ContactInfo contactInfo) {
        if (save(contactInfo)) {
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


    public ResponseDTO edit(ContactInfo contactInfo) {
        if (updateById(contactInfo)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        ContactInfo contactInfo = getById(id);
        if (contactInfo != null) {
            return Response.success(contactInfo);
        }
        return Response.error();
    }

    public ResponseDTO findAll() {
        List<ContactInfo> list = list();
        return Response.success(list);
    }

    public ResponseDTO list(Integer current, Integer size, ContactInfo contactInfo) {
        IPage<ContactInfo> config = new Page<>(current, size);
        QueryWrapper<ContactInfo> wrapper = new QueryWrapper<>();
        if (contactInfo.getCiName() != "" && contactInfo.getCiName() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("ci_name", contactInfo.getCiName());
        }
        if (contactInfo.getCiType() != "" && contactInfo.getCiType() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("ci_type", contactInfo.getCiType());
        }
        if (contactInfo.getCiTalentstatus() != "" && contactInfo.getCiTalentstatus() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("ci_talentStatus", contactInfo.getCiTalentstatus());
        }
        if (String.valueOf(contactInfo.getCiDate()) != "0000-00-00" && contactInfo.getCiDate() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("ci_date", contactInfo.getCiDate());
        }
        if (contactInfo.getCiCreator() != "" && contactInfo.getCiCreator() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("ci_creator", contactInfo.getCiCreator());
        }
        IPage<ContactInfo> page = page(config, wrapper);
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
        List<ContactInfo> list = list();
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
        List<ContactInfo> list = reader.readAll(ContactInfo.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }


}




