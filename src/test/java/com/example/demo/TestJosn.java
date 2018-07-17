package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Book;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 8:58 2018/5/29
 * @Modified By:
 */

public class TestJosn {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","xiaoming");
        map.put("age",18);
        map.put("sex","男");
        String jsonStr = JSON.toJSONString(map);
        Book book = new Book();
        String jsonEntity = JSON.toJSONString(book);
        System.out.println(jsonStr);
        System.out.println(jsonEntity);
        Map<String,Object> resultMap = JSON.parseObject(jsonStr,Map.class);
        System.out.println(resultMap.get("age"));
    }


}
