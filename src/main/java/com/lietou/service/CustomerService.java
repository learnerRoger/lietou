package com.lietou.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lietou.dto.Response;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.Customer;
import com.lietou.mapper.CustomerMapper;
import com.lietou.util.HutoolExcelUtil;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> {

    public ResponseDTO add(Customer customer) {
        if (save(customer)) {
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

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO edit(Customer customer) {
        if (updateById(customer)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Customer customer = getById(id);
        if (customer != null) {
            return Response.success(customer);
        }
        return Response.error();
    }

    public ResponseDTO findAll() {
        List<Customer> list = list();
        return Response.success(list);
    }

    /**
     * 分页查询
     *
     * @param current
     * @param size
     * @param customer
     * @return
     */
    public ResponseDTO list(Integer current, Integer size, Customer customer) {
        IPage<Customer> config = new Page<>(current, size);
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        if (customer.getCName() != "" && customer.getCName() != null) {
            wrapper.like("c_name", customer.getCName());
        }
        if (customer.getCSuperiorCompany() != null) {
            wrapper.like("c_superiorCompany", customer.getCSuperiorCompany());
        }
        if (customer.getCCompanyType() != null) {
            wrapper.eq("c_companyType", customer.getCCompanyType());
        }
        if (customer.getCImportance() != null) {
            wrapper.eq("c_importance", customer.getCImportance());
        }
        IPage<Customer> page = page(config, wrapper);
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
        List<Customer> list = list();
        HutoolExcelUtil.writeExcel(response, list, "客户信息表", Customer.class);
        return Response.success();
    }

    /**
     * 数据导入
     *
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Customer> list = HutoolExcelUtil.readExcel(inputStream, 1, Customer.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }


}
