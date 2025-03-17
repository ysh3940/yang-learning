package cn.yang.learning.notes.note2025项目;

public class mysql笔记 {

    // mysql分库分表，和redis分片一致
    // 设计案例：电商订单系统
    // 某电商平台采用分库分表（256分片）与Redis Cluster（256节点，每个节点管理64哈希槽）：
    // 写入：订单按用户ID分片写入数据库分片5，同步写入Redis槽位320-383（5 * 64=320）。
    // 查询：用户访问订单列表时，直接命中Redis分片5，仅未命中时穿透至数据库分片5。
    // 扩容：数据库分片扩容至512时，Redis槽位同步扩展至32768（通过虚拟节点调整），仅需迁移50%数据。



}
