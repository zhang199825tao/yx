package com.baizhi.dao;

import com.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {

    //分页查所有
    List<Video> queryByPage(@Param("start")int start,@Param("size")int size);

    //查询总条数
    int selectCount();

    //添加视频信息
    void addVideo(Video video);

    //删除视频
    void deleteVideo(String id);


}
