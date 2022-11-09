package cn.yang.learning.web;

import cn.yang.learning.autoconfigure.RestTemplateConfiguration;
import cn.yang.learning.core.web.BaseResponse;
import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Slf4j
@CrossOrigin
@RestController
public class RateLimitController {

    @Autowired
    RateLimiter rateLimiter;
    @Autowired
    RestTemplate restTemplate;
    @Resource(name = "commonExecutorService")
    ExecutorService commonExecutorService;

    @GetMapping("/rate/limit")
    @ResponseBody
    public BaseResponse rateLimit() {
        // 可以实时修改限流策略
        rateLimiter.setRate(20);

        // 100毫秒内，没拿到令牌，就直接进入服务降级
        // boolean tryAcquire = rateLimiter.tryAcquire(100, TimeUnit.MILLISECONDS);
        boolean tryAcquire = rateLimiter.tryAcquire();

        if (!tryAcquire) {
            return BaseResponse.fail("当前排队人数较多，请稍后再试");
        }


        return BaseResponse.success("ok");
    }

    public static void main(String[] args) throws Exception {
        log.error("RateLimiter test");

        RestTemplateConfiguration restTemplateConfiguration = new RestTemplateConfiguration();
        RestTemplate restTemplate = restTemplateConfiguration.restTemplate(restTemplateConfiguration.simpleClientHttpRequestFactory());

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("commonExecutorService" + "-%d")
                .setDaemon(true).build();
        ExecutorService executorService = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000), threadFactory,
                (r, executor) -> log.error("commonExecutorService.rejectedExecution The thread pool is full"));

        int count = 20;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0; i < count; ++i) {
            executorService.execute(() -> {
                ResponseEntity<BaseResponse> responseEntity = restTemplate.getForEntity("http://127.0.0.1:9090/rate/limit", BaseResponse.class);
                System.out.println(responseEntity.getStatusCode().toString() + " " + responseEntity.getBody().toString());

                countDownLatch.countDown();
            });
        }

        executorService.shutdown();

        countDownLatch.await();
        System.err.println("所有的子线程都执行结束");

    }

}
