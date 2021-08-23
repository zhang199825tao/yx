package com.baizhi.service;

import com.baizhi.annotation.CherryAnnotation;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.VoMonthAndCount;
import com.baizhi.utils.AboutFile;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {

        List<User> users = userDao.selectByPage((page - 1) * size, size);

        HashMap<String, Object> map = new HashMap<>();
        map.put("users", users);

        int sumcount = userDao.selectCount();//总条数
        int sumpage=0;//总页数
        if (sumcount%size==0){
            map.put("sumpage", sumcount/size);
        }else {
            map.put("sumpage",(sumcount/size)+1);
        }
        return map;
    }

    @CherryAnnotation
    @Override
    public void update(User user) {
        if (user.getStatus()==1){
            user.setStatus(0);
        }else {
            user.setStatus(1);
        }
        userDao.updateStatus(user);
    }

    @CherryAnnotation
    @SneakyThrows
    @Override
    public void addUser(MultipartFile photo, String username, String phone, String brief) {
        //String filename = "java2001/"+photo.getOriginalFilename();
        AboutFile.uploadHeadimg(photo,"java2001/"+photo.getOriginalFilename());
        User user = new User(UUID.randomUUID().toString(), username, phone, "http://zhangtzo-yx.oss-cn-huhehaote.aliyuncs.com/java2001/"+photo.getOriginalFilename(),brief , null, new Date(), 0);
        userDao.addUser(user);
    }

    @CherryAnnotation
    @Override
    public void delete(String id) {
        User user = userDao.selectUser(id);
        String headimg = user.getHeadimg();
        String substring = headimg.substring(headimg.lastIndexOf("/")+1, (headimg.length()));
        String s = "java2001/"+substring;
        AboutFile.deleteHeadimg(s);
        userDao.deleteUser(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<VoMonthAndCount> selectManOrWoman(String sex) {
        return userDao.selectManOrWoman(sex);
    }
}
