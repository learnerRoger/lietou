package com.lietou.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lietou.dto.Response;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.Docs;
import com.lietou.enums.BusinessStatusEnum;
import com.lietou.exception.ServiceException;
import com.lietou.mapper.DocsMapper;
import com.lietou.util.HutoolExcelUtil;
import com.lietou.vo.StaffDocsVO;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lietou
 * @Date 2023/2/24
 * @Version 1.0
 */
@Service
public class DocsService extends ServiceImpl<DocsMapper, Docs> {
    private static final MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://152.53.54.231:19000")
                    .credentials("t2efEwo96X6PFMQOISF5", "aU66uwnvqz7MZ8ImzTuydkuX7HGdnN3FfaJYcFOe")
                    .build();

    @Value("${file.upload.path}") // 引入上传文件的存储路径
    private String fileUploadPath;

    @Resource
    private DocsMapper docsMapper;

    /**
     * 文件上传
     *
     * @param uploadFile
     * @return
     */
    public ResponseDTO upload(MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        if (StrUtil.isNotBlank(token)) {
            // 获取token中的id
            Integer staffId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
            File fold = new File(fileUploadPath);
            // 若存储上传文件的文件夹不存在，则创建
            if (!fold.exists()) {
                fold.mkdirs();
            }
            // 判断上传的文件是否为空
            if (!uploadFile.isEmpty()) {
                String originalFilename = uploadFile.getOriginalFilename(); // 获取文件的原名称
                String extName = FileUtil.extName(originalFilename); // 获取文件的后缀名
                String filename = IdUtil.fastSimpleUUID().substring(2, 22) + "." + extName; // 文件名
                // 获取文件的md5信息
                String md5 = SecureUtil.md5(uploadFile.getInputStream());
                List<Docs> docsList = list(new QueryWrapper<Docs>().eq("md5", md5));
                // 若文件已经存在，则不用上传
                if (docsList != null && docsList.size() > 0) {
                    filename = docsList.get(0).getName();
                } else {
                    UploadToMinio(uploadFile,filename);
//                    File file = new File(fileUploadPath + filename);
//                    // 将文件存储到磁盘
//                    uploadFile.transferTo(file);
                }
                // 将文件数据保存到数据库
                Docs docs = new Docs();
                docs.setName(filename);
                docs.setStaffId(staffId); // 文件上传者
                docs.setType(extName);
                docs.setOldName(originalFilename);
                docs.setMd5(md5);
                docs.setSize(uploadFile.getSize() / 1024); // KB
                if (save(docs)) {
                    return Response.success("文件上传成功！", docs);
                }
                throw new ServiceException(BusinessStatusEnum.ERROR);
            }
            throw new ServiceException(BusinessStatusEnum.FILE_NOT_EXIST);
        }
        throw new ServiceException(BusinessStatusEnum.TOKEN_NOT_EXIST);
    }

    /**
     *  将文件上传到minio
     * @param fileName
     * @param fileName
     */
    public void UploadToMinio(MultipartFile uploadFile,String fileName) {
        try {
            InputStream inputStream = uploadFile.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("cloud")
                            .object(fileName)
                            .contentType(uploadFile.getContentType())
                            .stream(inputStream, inputStream.available(), -1)
                            .build());
            System.out.println("上传成功！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("上传失败！");
        }

    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param response
     * @return
     */
    public ResponseDTO download(String fileName, HttpServletResponse response) {
        try {
            InputStream file = minioClient.getObject(GetObjectArgs.builder().bucket("cloud").object(fileName).build());
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode(fileName.substring(fileName.lastIndexOf("/") + 1), StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            // 获取输出流
            ServletOutputStream servletOutputStream = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = file.read(buffer)) > 0) {
                servletOutputStream.write(buffer, 0, len);
            }
            servletOutputStream.flush();
            file.close();
            servletOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.success();
    }


    public ResponseDTO add(Docs docs) {
        if (save(docs)) {
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


    public ResponseDTO edit(Docs docs) {
        if (updateById(docs)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Docs docs = getById(id);
        if (docs != null) {
            return Response.success(docs);
        }
        return Response.error();
    }


    public ResponseDTO list(Integer current, Integer size, String oldName, String staffName) {
        if (oldName == null) {
            oldName = "";
        }
        if(staffName == null){
            staffName = "";
        }
        IPage<StaffDocsVO> config = new Page<>(current, size);
        IPage<StaffDocsVO> page = this.docsMapper.listStaffDocsVO(config, oldName,staffName);
        // 将响应数据填充到map中
        Map map = new HashMap();
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return Response.success(map);
    }

    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<Docs> list = list();
        HutoolExcelUtil.writeExcel(response, list, "文件信息表", Docs.class);
        return Response.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Docs> list = HutoolExcelUtil.readExcel(inputStream, 1, Docs.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }


}
