package cn.yang.learning.web;

import cn.yang.learning.autoconfigure.RestTemplateConfiguration;
import cn.yang.learning.core.web.BaseResponse;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@Slf4j
@CrossOrigin
@RestController
public class HystrixController {

    // -----------------------------------------------------------------------
    // 超时降级策略
    @GetMapping("/hystrix/timeout")
    @ResponseBody
    @HystrixCommand(commandKey = "/hystrix/timeout", commandProperties = {@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")}, fallbackMethod = "fallbackHystrixTimeout")
    public BaseResponse hystrixTimeout(String param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("hystrixTimeout.interrupted.error = {}", e.getMessage());
        }
        return BaseResponse.success("ok");
    }

    public BaseResponse fallbackHystrixTimeout(String param) {
        return BaseResponse.fail("Hystrix time out = " + param);
    }


    // -----------------------------------------------------------------------
    // 限流策略-信号量方式
    @GetMapping("/hystrix/limit")
    @HystrixCommand(commandKey = "/hystrix/limit", commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "1")}, fallbackMethod = "fallbackHystrixLimit")
    public BaseResponse hystrixLimit(String param) {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            log.error("hystrixLimit.interrupted.error = {}", e.getMessage());
//        }
        return BaseResponse.success(param);
    }

    public BaseResponse fallbackHystrixLimit(String param) {
        return BaseResponse.fail("限流策略-信号量方式-" + param);
    }

    public static void main(String[] args) throws Exception {
        log.error("HystrixController test");

        RestTemplateConfiguration restTemplateConfiguration = new RestTemplateConfiguration();
        RestTemplate restTemplate = restTemplateConfiguration.restTemplate(restTemplateConfiguration.simpleClientHttpRequestFactory());

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("commonExecutorService" + "-%d")
                .setDaemon(true).build();
        ExecutorService executorService = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000), threadFactory,
                (r, executor) -> log.error("commonExecutorService.rejectedExecution The thread pool is full"));

        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0; i < count; ++i) {
            executorService.execute(() -> {
                ResponseEntity<BaseResponse> responseEntity = restTemplate.getForEntity("http://127.0.0.1:9090/hystrix/limit?param=111xxx", BaseResponse.class);
                System.out.println(responseEntity.getStatusCode().toString() + " " + responseEntity.getBody().toString());

                countDownLatch.countDown();
            });
        }

        executorService.shutdown();

        countDownLatch.await();
        System.err.println("所有的子线程都执行结束");

    }


}
