package com.lietou.controller;

import com.deepoove.poi.XWPFTemplate;
import com.lietou.dto.Response;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.Contracts;
import com.lietou.service.ContractsService;
import com.lietou.util.WordGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/contracts")
public class ContractsController {
    @Resource
    private ContractsService contractsService;
    private WordGenerator wordGenerator;

    @ApiOperation("新增")
    @PostMapping
    public ResponseDTO add(@RequestBody Contracts contracts) {
        return this.contractsService.add(contracts);
    }

    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.contractsService.deleteById(id);
    }

    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.contractsService.deleteBatch(ids);
    }

    @ApiOperation("编辑更新")
    @PutMapping
    public ResponseDTO edit(@RequestBody Contracts contracts) {
        return this.contractsService.edit(contracts);
    }

    @ApiOperation("查询")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.contractsService.findById(id);
    }

    @ApiOperation("查询所有")
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.contractsService.findAll();
    }

    @ApiOperation("分页条件查询")
    @PostMapping("/page")
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, @RequestBody  Contracts contracts) {
        return this.contractsService.list(current, size, contracts);
    }

    @ApiOperation("数据导出接口")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.contractsService.export(response);
    }

    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.contractsService.imp(file);
    }

    @ApiOperation("下载合同")
    @PutMapping("/down")
    public ResponseDTO down(@RequestBody Contracts contracts) throws IOException {
        return this.contractsService.down(contracts);
    }
}

