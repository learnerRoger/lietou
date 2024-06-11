package com.lietou.controller;

import com.lietou.service.CustomerContactService;
import com.lietou.entity.CustomerContact;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.Contracts;
import com.lietou.service.ContractsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * 联系人信息表 前端控制器
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/customer-contact")
public class CustomerContactController {
    @Resource
    private CustomerContactService customerContactService;

    @ApiOperation("新增")
    @PostMapping
    public ResponseDTO add(@RequestBody CustomerContact customerContact) {
        return this.customerContactService.add(customerContact);
    }

    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.customerContactService.deleteById(id);
    }

    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.customerContactService.deleteBatch(ids);
    }

    @ApiOperation("编辑更新")
    @PutMapping
    public ResponseDTO edit(@RequestBody CustomerContact customerContact) {
        return this.customerContactService.edit(customerContact);
    }

    @ApiOperation("查询")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.customerContactService.findById(id);
    }

    @ApiOperation("查询所有")
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.customerContactService.findAll();
    }

    @ApiOperation("分页条件查询")
    @GetMapping
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, String name) {
        return this.customerContactService.list(current, size, name);
    }

    @ApiOperation("数据导出接口")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.customerContactService.export(response);
    }

    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.customerContactService.imp(file);
    }


}

