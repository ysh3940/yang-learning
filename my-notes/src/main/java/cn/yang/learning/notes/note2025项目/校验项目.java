package cn.yang.learning.notes.note2025项目;

public class 校验项目 {

    // 详细流程 TODO


    // 难点 TODO


    // 成长 TODO


    // 判断白名单-线程池
    //     1、高并发IO密集型
    //     实际配置：public static final ThreadPoolExecutor SKU_CHECK_SERVICE_EXECUTOR = new ThreadPoolExecutor(
    //            16, 32,
    //            30, TimeUnit.SECONDS,
    //            new ArrayBlockingQueue<>(2048),
    //            new CenterThreadFactory("SKU_CHECK_SERVICE_EXECUTOR"),
    //            new ThreadPoolExecutor.CallerRunsPolicy());
    //     参考：int corePoolSize = Runtime.getRuntime().availableProcessors() * 2; // 16核 → 32
    //          int maxPoolSize = corePoolSize * 2; // 64
    //          BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);
    //          ThreadPoolExecutor executor = new ThreadPoolExecutor(
    //          corePoolSize, maxPoolSize, 30, TimeUnit.SECONDS,
    //          queue, new ThreadPoolExecutor.CallerRunsPolicy()
    //          );
    //     2、CPU密集型
    //     参考：int corePoolSize = Runtime.getRuntime().availableProcessors() + 1; // 16核 → 17
    //          int maxPoolSize = corePoolSize; // 固定线程数
    //          BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    //          ThreadPoolExecutor executor = new ThreadPoolExecutor(
    //          corePoolSize, maxPoolSize, 0, TimeUnit.MILLISECONDS,
    //          queue, new ThreadPoolExecutor.AbortPolicy()
    //          );



}
