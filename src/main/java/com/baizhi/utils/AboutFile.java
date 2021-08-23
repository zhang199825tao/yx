package com.baizhi.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class AboutFile {

//    //产品名称:云通信短信API产品,开发者无需替换
//    static final String product = "Dysmsapi";
//    //产品域名,开发者无需替换
//    static final String domain = "dysmsapi.aliyuncs.com";
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String endpoint = "https://oss-cn-huhehaote.aliyuncs.com";
    static final String accessKeyId = "LTAI5t8d94hVu281cQsfxDsa";
    static final String accessKeySecret = "YEhyPb2S7oz5nxqTeDdqx1uWr0teCT";
    private static boolean running = false;

    //上传头像到阿里云
    public static void uploadHeadimg(MultipartFile photo,String filename) throws IOException {
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

// 填写Byte数组。
        byte[] content = photo.getBytes();
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("zhangtzo-yx", filename, new ByteArrayInputStream(content));
// 关闭OSSClient。


        ossClient.shutdown();
    }
    //从阿里云删除头像
    public static void deleteHeadimg(String filename){
// 填写Bucket名称。
        String bucketName = "zhangtzo-yx";
// 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String objectName = filename;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);

// 关闭OSSClient。
        ossClient.shutdown();
    }


    //视频截帧
    public static String uploadVideoImg(String filename){
// 填写Bucket名称。
        String bucketName = "zhangtzo-yx";
// 填写视频文件的完整路径。若视频文件不在Bucket根目录，需携带文件访问路径，例如examplefolder/videotest.mp4。
        String objectName = filename;
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 使用精确时间模式截取视频50s处的内容，输出为JPG格式的图片，宽度为800，高度为600。
        String style = "video/snapshot,t_3000,f_jpg,w_800,h_600";
// 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);
// 关闭OSSClient。

        InputStream inputStream = null;
        try{
            inputStream = new URL(signedUrl.toString()).openStream();
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] split = objectName.split("\\.");
        ossClient.putObject(bucketName,split[0]+".jpg" , inputStream);
        ossClient.shutdown();

        return split[0]+".jpg";
    }

    //从阿里云下载文件
    public static void downFile(String filename){
// 填写Bucket名称。
        String bucketName = "zhangtzo-yx";
// 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        String objectName = "java2001/"+filename;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
// 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("D:\\IDEACode\\yx_zt\\src\\main\\webapp\\headimg\\"+filename));

// 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void sendSms(String phone , String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5t8d94hVu281cQsfxDsa", "YEhyPb2S7oz5nxqTeDdqx1uWr0teCT");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");// 访问的域名
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "13074747123");//接收短信的手机号
        request.putQueryParameter("SignName", "应学APP");//短信签名名称
        request.putQueryParameter("TemplateCode", "SMS_221732234");//短信模板id
        request.putQueryParameter("TemplateParam", "{\"code\":\"1111\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getHttpStatus());
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //删除指定文件
    public static void deleteAnyone(String FileName){
            File file = new File(FileName);
            //判断文件是否存在
            if (file.exists() == true){
            file.delete();
        }
    }
}
