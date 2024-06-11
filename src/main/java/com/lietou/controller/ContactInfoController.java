package com.lietou.controller;

import com.lietou.dto.ResponseDTO;
import com.lietou.entity.ContactInfo;
import com.lietou.service.ContactInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 * 联系信息表 前端控制器
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/contact-info")
public class ContactInfoController {
    @Resource
    private ContactInfoService contactInfoService;

    @ApiOperation("新增")
    @PostMapping
    public ResponseDTO add(@RequestBody ContactInfo contactInfo) {
        return this.contactInfoService.add(contactInfo);
    }

    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.contactInfoService.deleteById(id);
    }

    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.contactInfoService.deleteBatch(ids);
    }

    @ApiOperation("编辑更新")
    @PutMapping
    public ResponseDTO edit(@RequestBody ContactInfo contactInfo) {
        return this.contactInfoService.edit(contactInfo);
    }

    @ApiOperation("查询")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.contactInfoService.findById(id);
    }

    @ApiOperation("查询所有")
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.contactInfoService.findAll();
    }

    @ApiOperation("分页条件查询")
    @PostMapping("/page")
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, @RequestBody  ContactInfo contactInfo) {
        return this.contactInfoService.list(current, size, contactInfo);
    }

    @ApiOperation("数据导出接口")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.contactInfoService.export(response);
    }

    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.contactInfoService.imp(file);
    }


}

