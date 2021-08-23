package com.baizhi.controller;

import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("queryallbypage")
    public Map<String,Object> queryAllByPage(Integer page){
        int size = 2;
        return videoService.queryByPage(page, size);
    }


    @RequestMapping("add")
    public void add(MultipartFile video,String title,String brief,String id){ ;
        videoService.addVideo(video, title, brief, id);

    }


    @RequestMapping("delete")
    public void delete(String id,String videopath){
        videoService.deleteVideo(id, videopath);
    }
}
