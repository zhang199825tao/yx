package com.baizhi;

import com.baizhi.dao.VideoDao;
import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class YxZtApplicationTests {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private VideoService videoService;

    @Test
    void contextLoads() {

//        List<Video> videos = videoDao.queryByPage(0, 1);
//        for (Video video : videos) {
//            System.out.println(video);
//        }
        videoDao.addVideo(new Video("23", "1", "1", "1", "1", new Date(),
                new Category("1", "XIAOWANG", null, null),
                new User(null, "2", null, null, null, null, null, 1), null));
    }

    @Test
    void contextLoads2() {
        Map<String, Object> map = videoService.queryByPage(1, 2);
        System.out.println(map);
    }

}
