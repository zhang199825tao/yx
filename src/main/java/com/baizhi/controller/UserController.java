package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.User;
import com.baizhi.entity.VoMonthAndCount;
import com.baizhi.service.UserService;
import com.baizhi.utils.AboutFile;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("queryallbypage")
    public Map<String,Object> queryAllByPage(int page){

        int size = 3;

        Map<String, Object> map = userService.queryByPage(page, size);

        return map;
    }

    @RequestMapping("updateuserstatus")
    public String updateUserStatus(@RequestBody User user){
        userService.update(user);
        return null;
    }

    @RequestMapping("add")
    public void addUser(MultipartFile photo,String username,String phone,String brief) throws IOException {
        userService.addUser(photo, username, phone, brief);
    }

    @RequestMapping("delete")
    public void delete(String id){
        userService.delete(id);
    }

    @RequestMapping("exportuser")
    public void exportUser() throws IOException {
        List<User> users = userService.findAll();
        for (User user : users) {
            String headimg = user.getHeadimg();
            String substring = headimg.substring(headimg.lastIndexOf("/")+1);
            AboutFile.downFile(substring);
            user.setHeadimg("D:\\IDEACode\\yx_zt\\src\\main\\webapp\\headimg\\"+substring);
        }
        //参数：标题，表名，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有视频用户","用户表"),User.class, users);
        workbook.write(new FileOutputStream("E:/easypoi-user.xls"));

        List<User> users2 = userService.findAll();
        for (User user : users2) {
            String headimg = user.getHeadimg();
            String substring = headimg.substring(headimg.lastIndexOf("/")+1);
            String filename = "D:\\IDEACode\\yx_zt\\src\\main\\webapp\\headimg\\"+substring;
            AboutFile.deleteAnyone(filename);
        }
        workbook.close();
    }

    @RequestMapping("registCount")
    public Map<String,Object> registCount(){
        List<VoMonthAndCount> voMonthAndCounts = userService.selectManOrWoman("男");
        List<VoMonthAndCount> voMonthAndCounts1 = userService.selectManOrWoman("女");
        List<String> data = new ArrayList<>();
        List<Integer> manCount = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0);
        List<Integer> womanCount = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0);
        for(int i = 1;i<=12;i++) {//第一次代表一月
            data.add(i + "月");
        }
        //然后查看集合中是否有一月出生的，如果有：获取对应的值，没有，定义为0
        for (VoMonthAndCount voMonthAndCount : voMonthAndCounts) {
            manCount.set((voMonthAndCount.getMonth()) - 1, voMonthAndCount.getCount());
        }
        for (VoMonthAndCount voMonthAndCount : voMonthAndCounts1) {
            womanCount.set(voMonthAndCount.getMonth()-1, voMonthAndCount.getCount());
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("manCount", manCount);
        map.put("womanCount", womanCount);

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-48c84eb5810d4f20b0ab85b8b9a10df0");
        //频道       什么消息
        goEasy.publish("aa", JSONObject.toJSONString(map));

        return map;
    }

}
