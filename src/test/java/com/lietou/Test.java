package com.lietou;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

public class Test {
    private static final MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://152.53.54.231:19000")
                    .credentials("minio", "3k8rXiJaxbbrJRDS")
                    .build();
    @org.junit.Test
    public void testUploadObject() {
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket("cloud")
                    .object("夏色四葉草-美术设定集-.pdf")
                    .filename("D:\\BaiduNetdiskDownload\\siyecao\\夏色四葉草-美术设定集-.pdf")
                    .build();
            minioClient.uploadObject(uploadObjectArgs);
            System.out.println("上传成功！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("上传失败！");
        }

    }

}
