package cn.yang.learning.notes;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;

@Slf4j
public class 红包金额算法 {

    @Test
    public void test() {
        // 先平方，再生成平方范围内的随机数，再开方，那么概率就不再是平均的了。
        double m = 10;// 100元10个人
        double m2 = Math.pow(m, 2);

        Random random = new Random();
        log.info(Math.sqrt(Double.valueOf((double) random.nextInt((int) m2))) + "");

    }

}
