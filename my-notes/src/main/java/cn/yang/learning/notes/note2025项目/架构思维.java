package cn.yang.learning.notes.note2025项目;

public class 架构思维 {

    // 架构思维体现checklist

    // 代码层面：代码review

    // 系统层面
    //是否有容量评估文档（QPS/TPS预估）
    //关键链路是否有降级方案（如Hystrix熔断配置）
    //是否考虑数据一致性（最终一致/强一致方案选择）

    // 运维层面
    //监控埋点是否覆盖核心指标（成功率/耗时/异常码）
    //是否有自动化巡检脚本（如定时核对缓存与DB数据）


}
