package com.cyz.boot.controller;

import com.cyz.boot.dto.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:cyz
 * @Date:2020/4/23 22:22
 * @Description:
 */
@RestController
@Slf4j
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("jsonRedisTemplate")
    private RedisTemplate<String,Object> jsonRedisTemplate;

    @PostMapping("/setString")
    public String setRedis(String value){
        stringRedisTemplate.opsForValue().set("setValue",value);
        log.info("存储字符串：{}",value);
        return "ok";
    }

    @PostMapping("/setJson")
    public String setJson(Book book){
        jsonRedisTemplate.opsForValue().set("book:1",book);
        Book redisBook = (Book) jsonRedisTemplate.opsForValue().get("book:1");
        log.info("存储json：{}",redisBook.toString());
        return "ok";
    }

}
