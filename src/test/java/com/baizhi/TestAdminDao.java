package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YxZtApplication.class)
public class TestAdminDao {

    /**
     *
     */
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        Admin zt = adminService.queryByUsername("zt");
        System.out.println(zt);

    }

    @Test
    public void test2(){
//        List<User> users = userDao.selectByPage(0, 3);
//        for (User user : users) {
//            System.out.println(user);
//        }
        userService.delete("7f27c941-cda2-4d2b-843f-1f4d9c40cfe3");




    }

}
