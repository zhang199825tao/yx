package com.baizhi;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = YxZtApplication.class)
public class TestUpload {

    @Test
    public void test(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-huhehaote.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5t8d94hVu281cQsfxDsa";
        String accessKeySecret = "YEhyPb2S7oz5nxqTeDdqx1uWr0teCT";
        String bucketName = "zhangtzo-yxjava";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建存储空间。
        ossClient.createBucket(bucketName);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test2(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-huhehaote.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5t8d94hVu281cQsfxDsa";
        String accessKeySecret = "YEhyPb2S7oz5nxqTeDdqx1uWr0teCT";
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

// 填写Byte数组。
        byte[] content = "Hello OSS".getBytes();
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("zhangtzo-yxjava", "java2011/"+1+".png", new ByteArrayInputStream(content));

// 关闭OSSClient。
        ossClient.shutdown();
    }

//    上传文件流
    @Test
    public void  test3() throws FileNotFoundException {
        String endpoint = "https://oss-cn-huhehaote.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5t8d94hVu281cQsfxDsa";
        String accessKeySecret = "YEhyPb2S7oz5nxqTeDdqx1uWr0teCT";
        String s = "1.png";
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("E:\\百知教育\\javaEE\\后期项目\\1.png");
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("zhangtzo-yx", "java2001/"+s, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test4(){
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "https://oss-cn-huhehaote.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5t8d94hVu281cQsfxDsa";
        String accessKeySecret = "YEhyPb2S7oz5nxqTeDdqx1uWr0teCT";
// 填写Bucket名称。
        String bucketName = "zhangtzo-yx";
// 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String objectName = "java2001/i.png";

        //http://zhangtzo-yx.oss-cn-huhehaote.aliyuncs.com/java2001/2.png

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


// 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);

// 关闭OSSClient。
        ossClient.shutdown();

    }
}
