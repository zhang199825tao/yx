package com.baizhi;


import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.VoMonthAndCount;
import com.baizhi.utils.RandUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YxZtApplication.class)
public class TestUserDao {

    /**
     *
     */
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        List<User> users = userDao.selectByPage(0, 3);
        for (User user : users) {
            System.out.println(user);
        }

        int i = userDao.selectCount();
        System.out.println(i);

    }

    @Test
    public void test2(){
        List<VoMonthAndCount> man = userDao.selectManOrWoman("男");
        for (VoMonthAndCount voMonthAndCount : man) {
            System.out.println(voMonthAndCount);
        }
    }

}
