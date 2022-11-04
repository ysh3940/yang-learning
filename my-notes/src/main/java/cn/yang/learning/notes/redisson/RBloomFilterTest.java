package cn.yang.learning.notes.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Slf4j
public class RBloomFilterTest {

    @Test
    public void test() {
        // 默认连接地址 127.0.0.1:6379
        // RedissonClient redisson = Redisson.create();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("sample-1");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add("field1Value");
        bloomFilter.add("field2Value");
        bloomFilter.add("field5Value");
        bloomFilter.add("field8Value");

        log.info(bloomFilter.contains("field1Value") + "");
        log.info(bloomFilter.contains("field8Value-1") + "");

        // 初始化1000000条数据到过滤器中
        int total = 10000;
        for (int i = 0; i < total; i++) {
            log.info(i + "");
            bloomFilter.add(i + "");
        }

        // 匹配已在过滤器中的值，是否有匹配不上的
        for (int i = 0; i < total; i++) {
            if (!bloomFilter.contains(i + "")) {
                log.info("有坏人逃脱了~~~");
            }
        }

        // 匹配不在过滤器中的10000个值，有多少匹配出来
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if (bloomFilter.contains(i + "")) {
                count++;
            }
        }
        log.info("误伤的数量：" + count);
    }

}
