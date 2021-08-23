package com.baizhi;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class TestGoEasy {

    @Test
    public void test(){
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-48c84eb5810d4f20b0ab85b8b9a10df0");
                       //频道       什么消息
        goEasy.publish("aa", "hello   goeasy");

    }
    @Test
    public void test2(){
        for (int i =1;i<20;i++){

            List<String> data = new ArrayList<>();
            Random random = new Random();
            List<Integer> manCount = Arrays.asList(random.nextInt(300),random.nextInt(300),random.nextInt(300),random.nextInt(300),0,random.nextInt(300),0,0,0,0,0);
            List<Integer> womanCount = Arrays.asList(998,random.nextInt(300),442,56,25,17,8,0,99,random.nextInt(300),random.nextInt(300),random.nextInt(300),random.nextInt(300));
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", data);
            map.put("manCount", manCount);
            map.put("womanCount", womanCount);

            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-48c84eb5810d4f20b0ab85b8b9a10df0");
            //频道       什么消息
            goEasy.publish("aa", JSONObject.toJSONString(map));
        }


    }


}
