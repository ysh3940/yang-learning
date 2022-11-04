package cn.yang.learning.notes;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 应用场景：
//订单超过 30 分钟未支付，则自动取消。
//
//外卖商家超时未接单，则自动取消。
//
//医生抢单电话点诊，超过 30 分钟未打电话，则自动退款。
//
//等等场景都可以用定时任务去轮询实现，但是当数据量过大的时候，高频轮询数据库会消耗大量的资源，此时用延迟队列来应对这类场景比较好。
@Slf4j
public class DelayQueue {

    @Test
    public void test() throws Exception {
        java.util.concurrent.DelayQueue<Message> queue = new java.util.concurrent.DelayQueue<>();

        long now = System.currentTimeMillis();

        // 启动一个线程从队列中取元素
        new Thread(() -> {
            while (true) {
                try {
                    // 将依次打印1000，2000，5000，7000，8000
                    System.out.println(queue.take().deadline - now);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 添加5个元素到队列中
        queue.add(new Message(now + 5000));
        queue.add(new Message(now + 8000));
        queue.add(new Message(now + 2000));
        queue.add(new Message(now + 1000));
        queue.add(new Message(now + 7000));

        Thread.sleep(1000 * 1000);
    }

    public class Message implements Delayed {

        long deadline;

        public Message(long deadline) {
            this.deadline = deadline;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return deadline - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return String.valueOf(deadline);
        }

    }

}
