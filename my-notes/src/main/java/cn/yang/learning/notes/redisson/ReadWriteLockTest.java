package cn.yang.learning.notes.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ReadWriteLockTest {

    @Test
    public void test() throws Exception {
        // 默认连接地址 127.0.0.1:6379
        // RedissonClient redisson = Redisson.create();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RReadWriteLock rwlock = redisson.getReadWriteLock("anyRWLock-1");
        // 最常见的使用方法
        // rwlock.readLock().lock();
        // 或
        // rwlock.writeLock().lock();


        // log.info("write lock " + rwlock.writeLock().tryLock());
        // rwlock.writeLock().lock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(() -> {
            // 最常见的使用方法
            // rwlock.writeLock().lock();
            // log.info(Thread.currentThread().getId() + " write lock ok");
        });
        for (int i = 1; i <= 5; ++i) {
            executorService.execute(() -> {
                // 最常见的使用方法
                rwlock.readLock().lock();
                log.info(Thread.currentThread().getId() + " read lock ok");
            });
        }

        executorService.shutdown();
        Thread.sleep(1000 * 100);
    }


}
