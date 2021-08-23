package com.baizhi.service;


import com.baizhi.entity.User;
import com.baizhi.entity.VoMonthAndCount;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String,Object> queryByPage(int page, int size);

    void update(User user);

    //添加用户
    void addUser(MultipartFile photo, String username, String phone, String brief);

    //删除用户
    void delete(String id);

    //查所有
    List<User> findAll();

    //根据注册月份查询男生人数
    List<VoMonthAndCount> selectManOrWoman(String sex);
}
