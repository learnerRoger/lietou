package com.lietou.controller;

import com.lietou.service.ProjectsService;
import com.lietou.entity.Projects;
import com.lietou.dto.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 * 项目表 前端控制器
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Resource
    private ProjectsService projectsService;

    @ApiOperation("新增")
    @PostMapping
    public ResponseDTO add(@RequestBody Projects projects) {
        return this.projectsService.add(projects);
    }

    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public ResponseDTO delete(@PathVariable Integer id) {
        return this.projectsService.deleteById(id);
    }

    @ApiOperation("批量逻辑删除")
    @DeleteMapping("/batch/{ids}")
    public ResponseDTO deleteBatch(@PathVariable List<Integer> ids) {
        return this.projectsService.deleteBatch(ids);
    }

    @ApiOperation("编辑更新")
    @PutMapping
    public ResponseDTO edit(@RequestBody Projects projects) {
        return this.projectsService.edit(projects);
    }

    @ApiOperation("查询")
    @GetMapping("/{id}")
    public ResponseDTO findById(@PathVariable Integer id) {
        return this.projectsService.findById(id);
    }

    @ApiOperation("查询所有")
    @GetMapping("/all")
    public ResponseDTO findAll() {
        return this.projectsService.findAll();
    }

    @ApiOperation("分页条件查询")
    @PostMapping("/page")
    public ResponseDTO list(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size, @RequestBody Projects projects) {
        return this.projectsService.list(current, size, projects);
    }

    @ApiOperation("数据导出接口")
    @GetMapping("/export")
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        return this.projectsService.export(response);
    }

    @ApiOperation("数据导入接口")
    @PostMapping("/import")
    public ResponseDTO imp(MultipartFile file) throws IOException {
        return this.projectsService.imp(file);
    }


}

