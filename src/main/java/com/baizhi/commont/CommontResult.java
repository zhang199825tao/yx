package com.baizhi.commont;

import java.util.HashMap;
import java.util.Map;

public class CommontResult {

    public static Map<String,Object> success(Object dat,String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("data", dat);
        map.put("message", msg);
        map.put("status", "100");

        return map;
    }

    public static Map<String,Object> fail(Object dat,String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("data", dat);
        map.put("message", msg);
        map.put("status", "104");
        return map;


    }

}
