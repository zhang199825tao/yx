package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("login")
    public Map<String, String> login(@RequestBody Admin admin, String name, HttpServletRequest request){
        Admin admin1 = adminService.queryByUsername(admin.getUsername());
        HashMap<String, String> map = new HashMap<>();
        if(admin1==null){
            map.put("error", "false");
            map.put("msg", "该用户不存在");
        }else {
            if(admin.getPassword().equals(admin1.getPassword())){
                map.put("success", "true");
                map.put("msg", "点击进入");
                map.put("username", admin1.getUsername());
                String sessionid = request.getSession(true).getId();
                ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
                valueOperations.set(sessionid, JSONObject.toJSONString(admin1),30, TimeUnit.MINUTES);
                map.put("token", sessionid);
            }else {
                map.put("error", "false");
                map.put("msg", "密码不正确");
            }
        }

        return map;
    }
}
