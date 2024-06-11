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

/**
 * <p>
 * 候选人表 服务类
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Service
public class CandidateService extends ServiceImpl<CandidateMapper, Candidate> {


    public ResponseDTO add(Candidate candidate) {
        if (save(candidate)) {
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


    public ResponseDTO edit(Candidate candidate) {
        if (updateById(candidate)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Candidate candidate = getById(id);
        if (candidate != null) {
            return Response.success(candidate);
        }
        return Response.error();
    }

    public ResponseDTO findAll() {
        List<Candidate> list = list();
        return Response.success(list);
    }

    public ResponseDTO list(Integer current, Integer size, Candidate candidate) {
        IPage<Candidate> config = new Page<>(current, size);
        QueryWrapper<Candidate> wrapper = new QueryWrapper<>();
        if (candidate.getTName() != "" && candidate.getTName() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("t_name", candidate.getTName());
        }
        if (candidate.getTDegree() != "" && candidate.getTDegree() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("t_degree", candidate.getTDegree());
        }
        if (candidate.getTMajor() != "" && candidate.getTMajor() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("t_major", candidate.getTMajor());
        }
        if (candidate.getTType() != "" && candidate.getTType() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("t_ype", candidate.getTType());
        }
        if (candidate.getTEducation() != "" && candidate.getTEducation() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("t_education", candidate.getTEducation());
        }
        if (candidate.getTAddress() != "" && candidate.getTAddress() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.like("t_address", candidate.getTAddress());
        }
        IPage<Candidate> page = page(config, wrapper);
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
        List<Candidate> list = list();
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
        List<Candidate> list = reader.readAll(Candidate.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }


}




