package com.cyz.boot.controller;

import com.cyz.boot.RedisLockHelper;
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
    RedisLockHelper redisLockHelper;

    private static final int TIMEOUT = 5*1000;

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

    @PostMapping(value = "/seckilling")
    public String Seckilling(String targetId){
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if(!redisLockHelper.lock(targetId,String.valueOf(time))){
            return "排队人数太多，请稍后再试.";
        }

        int surplusCount = 0;
        // 查询该商品库存，为0则活动结束 e.g. getStockByTargetId
        if(surplusCount==0){
            return "活动结束.";
        }else {
            // 下单 e.g. buyStockByTargetId

            //减库存 不做处理的话，高并发下会出现超卖的情况，下单数，大于减库存的情况。虽然这里减了，但由于并发，减的库存还没存到map中去。新的并发拿到的是原来的库存
            surplusCount =surplusCount-1;
            try{
                Thread.sleep(100);//模拟减库存的处理时间
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            // 减库存操作数据库 e.g. updateStockByTargetId

            // buyStockByTargetId 和 updateStockByTargetId 可以同步完成(或者事物)，保证原子性。
        }

        //解锁
        redisLockHelper.unlock(targetId,String.valueOf(time));

        return "恭喜您，秒杀成功。";
    }
}
