package com.baizhi.service;

import com.baizhi.entity.AppVideo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface VideoService {

    //分页查所有
    Map<String, Object> queryByPage(int start, int size);

    //添加业务
    void addVideo(MultipartFile video, String title, String brief, String id);

    //删除视频
    void deleteVideo(String id,String videopath);

    //擦汗所有
    List<AppVideo> findAll();
}
