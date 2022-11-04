package cn.yang.learning.notes.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RLockTest {

    @Test
    public void test() throws Exception {
        // 默认连接地址 127.0.0.1:6379
        // RedissonClient redisson = Redisson.create();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock-1");


        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 1; i <= 1; ++i) {
            executorService.execute(() -> {
                try {
                    // 最常见的使用方法
                    boolean flag = lock.tryLock(1, 10, TimeUnit.SECONDS);
                    log.info(Thread.currentThread().getId() + " lock " + flag);
                    if (!flag) {
                        return;
                    }
                    log.info("lock-1");

                    /**
                     * RLock本身有重入和检查thread id的机制
                     * 但因为一些原因决定包装它：
                     * 1. 每次RLock tryLock重入时都要更新过期时间，会导致实际锁定的时间超过我们设定的自动释放时间
                     * 2. tryLock可能会在已经超时释放锁之后再次获得锁，而这种情况其实应该抛异常说明超时
                     * 3. 用tryLock/unlock在Redis端统计重入次数的话，有时会增加通信次数，降低效率
                     */
                    lock.tryLock(1, 10, TimeUnit.SECONDS);
                    log.info("lock-2");
                } catch (Exception e) {
                }
            });
        }

        executorService.shutdown();


        Thread.sleep(1000 * 100);

//    lock.unlock();
//    lock.unlock();

    }

}
