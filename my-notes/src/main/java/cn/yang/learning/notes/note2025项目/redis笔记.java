package cn.yang.learning.notes.note2025项目;

public class redis笔记 {

    // 1.Redis为什么单线程却能高效处理高并发？详细解答下，配合图文
    // 2.Redis Cluster 的分片数 是固定16438吗
    // 3.redis 同一个时间点过期大量数据会怎么样

    // redis集群中某个主节点故障，触发从节点升级为主节点
    // 1、导致这个分片的查询出现 3-4分钟的超时，导致mq消费逻辑异常，触发重试

}
