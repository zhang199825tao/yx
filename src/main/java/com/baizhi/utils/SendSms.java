package com.baizhi.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendSms {
    public static void main(String[] args) {
                                                                                // 您的AccessKey ID             // 您的AccessKey Secret
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
}