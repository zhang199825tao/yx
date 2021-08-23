package com.baizhi.controller;


import com.baizhi.commont.CommontResult;
import com.baizhi.entity.AppVideo;
import com.baizhi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/yingx/app")
@CrossOrigin
@RestController
public class AppController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("queryByReleaseTime")
    public Map<String,Object> queryByReleaseTime(){
        List<AppVideo> all = videoService.findAll();
        if (all.size()>0){
            return CommontResult.success(all, "查询成功");
        }else {
            return CommontResult.fail(all, "查询失败");
        }
    }

}
