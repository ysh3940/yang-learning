package cn.yang.learning.notes.redisson;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class Redis实现延时队列 {

    @Test
    public void test() throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        new Thread(new Runnable() {
            @Override
            public void run() {
                consumerDelayMessage(redisson);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                productionDelayMessage(redisson);
            }
        }).start();

        Thread.sleep(1000 * 1000);
    }

    // 消费者，取订单
    public static void consumerDelayMessage(RedissonClient redisson) {
        RScoredSortedSet<String> scoredSortedSet = redisson.getScoredSortedSet("orderId");
        while (true) {
            if (scoredSortedSet.size() > 0) {
                long score = scoredSortedSet.firstScore().longValue();
                long time = System.currentTimeMillis();
                long surplus = (score - time);
                if (surplus <= 0) {
                    String first = scoredSortedSet.first();
                    log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ":redis消费了一个任务：消费的订单OrderId为" + first);
                    // 取出对象
                    scoredSortedSet.remove(first);
                }
            } else {
                // 当set中没有数据时 终止循环
            }
        }
    }

    // 生产者,生成5个订单
    public void productionDelayMessage(RedissonClient redisson) {
        for (int i = 0; i < 5; i++) {
            Calendar instance = Calendar.getInstance();
            // 3秒后执行
            instance.add(Calendar.SECOND, 3 + i);

            RScoredSortedSet<String> scoredSortedSet = redisson.getScoredSortedSet("orderId");
            scoredSortedSet.add((instance.getTimeInMillis()), StringUtils.join("000000000", i + 1));

            log.info("生产订单: " + StringUtils.join("000000000", i + 1) + " 当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            log.info((3 + i) + "秒后执行");
        }
    }

}
