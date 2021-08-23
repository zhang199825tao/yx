package com.baizhi.dao;


import com.baizhi.entity.User;
import com.baizhi.entity.VoMonthAndCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    List<User> selectByPage(@Param("start") int start,@Param("size") int size);

    //查询总条数
    int selectCount();

    //修改当前用户状态
    void updateStatus(User user);

    //添加用户
    void addUser(User user);

    //删除用户
    void deleteUser(String id);

    //根据id查一个
    User selectUser(String id);

    //查所有用户
    List<User> findAll();

    //根据注册月份查询男生人数
    List<VoMonthAndCount> selectManOrWoman(String sex);





}
