package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
import jdk.internal.dynalink.beans.StaticClass;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.applet.Main;

import java.security.PublicKey;
import java.sql.SQLOutput;
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
        //userService.delete("7f27c941-cda2-4d2b-843f-1f4d9c40cfe3");
        System.out.println("11");
//        class HelloA{
//            public HelloA(){
//                System.out.println("helloA");
//            }
//            {
//                System.out.println("im   A  class");
//            }
//            static {
//                System.out.println("static A");
//            }
//        }
//
//        public HelloB extends HelloA{
//            public HelloB(){
//                System.out.println("b");
//            }
//            {
//                System.out.println("im  b");
//            }
//            static{
//                System.out.println(  "static b");
//            }
//
//            public static void main(String[] args){
//                System.out.println("main start");
//                new HelloB();
//                new HelloB();
//                System.out.println("main end");
//            }
//        }

        /*第一题：main start
               static A
               HelloB
               I m B class
               main end

*/
            int touHigh = 2;
            int jianHigh = 13 ;
            int kuang =18;
            for (int i=1;i<=touHigh+jianHigh;i++){
                for(int j =1;j<=kuang;j++){
                    //上三角
                    if (i<=touHigh) {
                        if(j>=(kuang/2+1)+1-i && j<=(kuang/2+1)-1+i){
                            System.out.print("*");
                        }else{
                            System.out.print("-");
                        }
                    }
                    //上三角一下部分
                    if (i>touHigh&&i<=jianHigh){
                        if(j>=(kuang/2+1)+2-i&&j<=kuang-3*(i-touHigh)){
                            System.out.print("*");
                        }
                        else if(j<(kuang/2+1)-3+i&&j>=3*(i-touHigh)){
                            System.out.print("*");
                        }
                        else {
                            System.out.print("-");
                        }
                    }
                }
                System.out.println("");
            }


    }


}
