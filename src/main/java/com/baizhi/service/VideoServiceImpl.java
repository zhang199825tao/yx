package com.baizhi.service;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.baizhi.annotation.CherryAnnotation;
import com.baizhi.dao.AppVideoDao;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.AppVideo;
import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.utils.AboutFile;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoDao videoDao;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {
        List<Video> videos = videoDao.queryByPage((page-1)*size, size);
        HashMap<String, Object> map = new HashMap<>();
        map.put("videos", videos);
        int sumcount = videoDao.selectCount();
        int sumpage = 0;
        if(sumcount%size==0){
            map.put("sumpage",sumcount/size );
        }else {
            map.put("sumpage", (sumcount/size)+1);
        }
        return map;
    }

    @CherryAnnotation
    @SneakyThrows
    @Override
    public void addVideo(MultipartFile video, String title, String brief, String id) {

        String filename = "java2001video/"+video.getOriginalFilename();
        AboutFile.uploadHeadimg(video, filename);//上传视频
        String s1 = AboutFile.uploadVideoImg(filename);//上传视频截贞
        String s = "http://zhangtzo-yx.oss-cn-huhehaote.aliyuncs.com/";
        //http://zhangtzo-yx.oss-cn-huhehaote.aliyuncs.com/java2001video/14c062f8969784ecb4ebb97a33d47f26.mp4

        Video video1 = new Video(UUID.randomUUID().toString(), title, brief, s+s1, s+filename,
                new Date(), new Category(id, null, null, null), new User("1", null, null  ,null ,null ,null ,null ,1 ), null);
        videoDao.addVideo(video1);
    }

    @CherryAnnotation
    @Override
    public void deleteVideo(String id,String videopath) {

//        http://zhangtzo-yx.oss-cn-huhehaote.aliyun
//         cs.com/java2001video/14c062f8969784ecb4ebb97a33d47f26.mp4
        String substring = videopath.substring(videopath.lastIndexOf("/")+1);//14c062f8969784ecb4ebb97a33d47f26.mp4
        AboutFile.deleteHeadimg("java2001video/"+substring);//删除视频
        //删除图片
        String replace = substring.replace(".mp4", ".jpg");//14c062f8969784ecb4ebb97a33d47f26.jpg
        AboutFile.deleteHeadimg("java2001video/"+replace);
        videoDao.deleteVideo(id);
    }


    @Autowired
    private AppVideoDao appVideoDao;

    @Override
    public List<AppVideo> findAll() {
        return appVideoDao.findAll();
    }
}
