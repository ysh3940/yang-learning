package cn.yang.learning.notes.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class RateLimiterTest {

    @Test
    public void test() throws Exception {
        // 默认连接地址 127.0.0.1:6379
        // RedissonClient redisson = Redisson.create();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RRateLimiter rateLimiter = redisson.getRateLimiter("myRateLimiter-1");
        // 初始化
        // 最大流速 = 每5秒钟产生10个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 10, 5, RateIntervalUnit.SECONDS);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 1; i <= 20; ++i) {
            executorService.execute(() -> {
                boolean flag = rateLimiter.tryAcquire();
                if (!flag) {
                    log.info(Thread.currentThread().getName() + "-" + false);
                    return;
                }
                log.info(Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
        Thread.sleep(1000 * 100);
    }

}
