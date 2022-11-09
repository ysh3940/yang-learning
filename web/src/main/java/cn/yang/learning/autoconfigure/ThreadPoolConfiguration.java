package cn.yang.learning.autoconfigure;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Slf4j
@Configuration
public class ThreadPoolConfiguration {

    @Bean("commonExecutorService")
    public ExecutorService commonExecutorService() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("commonExecutorService" + "-%d")
                .setDaemon(true).build();
        ExecutorService executorService = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000), threadFactory,
                (r, executor) -> log.error("commonExecutorService.rejectedExecution The thread pool is full"));
        return executorService;
    }


}
