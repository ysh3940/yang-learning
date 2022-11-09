package cn.yang.learning.autoconfigure;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfiguration {

    @Bean
    public RateLimiter initRateLimiter() {
        // 限流策略 ：1秒钟10个请求
        return RateLimiter.create(10);
    }

}
