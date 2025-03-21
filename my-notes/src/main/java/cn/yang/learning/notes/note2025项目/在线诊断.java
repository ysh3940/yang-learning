package cn.yang.learning.notes.note2025项目;

public class 在线诊断 {

    // -------------------------1、堆栈快照导出-------------------------
    // 1、概况
    // 堆使用大小
    //249.13 M
    //类数量
    //39.27 k
    //对象数量
    //4.28 m
    //类加载器数量
    //477
    //GC 根对象数量
    //5.33 k
    //创建时间
    //2025-03-21 10:00:43
    //系统位数
    //64 bit

    // 2、内存泄漏检测

    // 3、JVM详情
    // 堆大小（最大~最小）
    //2048m~2048m
    //Metaspace大小
    //512m~512m
    //最大直接内存
    //1228m
    //并发标记线程数
    //0
    //并行GC线程的数量
    //2
    //热点编译器线程数
    //2
    //垃圾收集器
    //G1GC
    //JVM辅助配置(推荐添加)
    //-XX:+heapDumpOnOutOfMemoryError
    //true
    //-xx:HeapDumpPath=
    ///export/Logs
    //-XX:+PrintGCDetails
    //true
    //-XX:+PrintGCDateStamps
    //true
    //-Xloggc:
    ///export/Logs/mac.soa.jd.com/gc.log

    // -Djex.dsn=http://fj4jazs66iwzpmyywii3d1f16ekiqv02@jex.jd.local/3669?in_app=com.jd&release=b4bf6a0 -agentpath:/export/Jex/libjexagent.so -Djava.util.logging.config.file=/export/Domains/theme-activity.jd.com/server1/conf/logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -javaagent:/export/servers/gaze/gaze-agent.jar -Xms2048m -Xmx2048m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=512m -XX:MaxDirectMemorySize=1228m -XX:ConcGCThreads=0 -XX:ParallelGCThreads=2 -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:CICompilerCount=2 -Djava.library.path=/usr/local/lib -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1HeapRegionSize=2m -XX:GCTimeRatio=99 -XX:G1NewSizePercent=35 -XX:G1MaxNewSizePercent=45 -XX:SurvivorRatio=8 -XX:MaxGCPauseMillis=150 -XX:MaxTenuringThreshold=10 -XX:G1MixedGCLiveThresholdPercent=70 -XX:G1OldCSetRegionThresholdPercent=10 -XX:InitiatingHeapOccupancyPercent=40 -XX:G1HeapWastePercent=5 -XX:+PrintGCDateStamps -Xloggc:/export/Logs/mac.soa.jd.com/gc.log -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=52001 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dump.prefix.name=zice -XX:HeapDumpPath=/export/Logs -Djava.awt.headless=true -Dsun.net.client.defaultConnectTimeout=60000 -Dsun.net.client.defaultReadTimeout=60000 -Djmagick.systemclassloader=no -Dnetworkaddress.cache.ttl=300 -Dsun.net.inetaddr.ttl=300 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18030 -Xbootclasspath/a:/export/servers/jdk1.8.0_60/lib/tools.jar -Dspring.profiles.active=jsf-provider-active -Ddeploy.app.name=jdos_theme-activity -Ddeploy.app.id=1329211 -Ddeploy.instance.id=0 -Ddeploy.dynamic.config.dir=WEBINFCLASSES -DJDOS_DATACENTER=HT -javaagent:/export/pfinder/theme-activity/lib/pfinder-profiler-agent-20240630.jar -Djava.endorsed.dirs= -Dcatalina.base=/export/Domains/theme-activity.jd.com/server1 -Dcatalina.home=/export/servers/tomcat6.0.33 -Djava.io.tmpdir=/export/Domains/theme-activity.jd.com/server1/temp

    // 4、类
    // 对象数量
    //Shallow Heap
    //Retained Heap

    // 5、线程
    // Name
    //Shallow Size
    //Retained Size
    //Locals' Retained
    //Context Class Loader
    //Daemon

    // 6、堆外内存
    // position
    //limit
    //capacity

    // -------------------------2、GC日志-------------------------
    // 1、基本信息
    //文件名
    //日志覆盖的时间段
    //	2025-03-20 15:07:15 ~ 03-21 10:31:15，时长 19.4 h
    //GC 算法
    //当前分析的时间段
    //G1 GC	2025-03-20 15:07:15 ~ 03-21 10:31:15 ，时长 19.4 h
    //JVM参数
    //GC相关参数
    //-XX:+UseG1GC
    //-XX:InitialHeapSize=2147483648
    //-XX:MaxDirectMemorySize=1287651328
    //-XX:MaxHeapSize=2147483648
    //-XX:MaxMetaspaceSize=536870912
    //-XX:MetaspaceSize=536870912
    //-XX:ConcGCThreads=0
    //-XX:ParallelGCThreads=2
    //-XX:G1HeapRegionSize=2097152
    //-XX:G1HeapWastePercent=5
    //-XX:G1MaxNewSizePercent=45
    //-XX:G1MixedGCLiveThresholdPercent=70
    //-XX:G1NewSizePercent=35
    //-XX:G1OldCSetRegionThresholdPercent=10
    //-XX:InitiatingHeapOccupancyPercent=40
    //-XX:MaxGCPauseMillis=150
    //-XX:GCTimeRatio=99
    //-XX:MaxTenuringThreshold=10
    //-XX:SurvivorRatio=8
    //-XX:+UseCompressedClassPointers
    //-XX:+UseCompressedOops
    //-XX:+PrintGC
    //-XX:+PrintGCDateStamps
    //-XX:+PrintGCDetails
    //-XX:+PrintGCTimeStamps
    //其它参数
    //-XX:CICompilerCount=2
    //-XX:CompressedClassSpaceSize=528482304
    //-XX:+HeapDumpOnOutOfMemoryError
    //-XX:HeapDumpPath=/export/Logs
    //-XX:+ManagementServer
    //-XX:+UnlockExperimentalVMOptions

    // 2、时间图
    // 年轻代容量、 容量：默认占堆内存的1/3，可通过参数-Xmn直接指定（如-Xmn1g），或通过-XX:NewRatio调整比例（如-XX:NewRatio=3表示年轻代占1/4）。
    //            使用特点：采用复制算法，Eden区与Survivor区（From/To）动态分配对象，存活对象经多次GC（默认15次）后晋升老年代。
    // 老年代使用、
    // 老年代容量、 容量：默认占堆内存的2/3，可通过-XX:NewRatio调整。例如，G1收集器中老年代Region占比超过45%（-XX:InitiatingHeapOccupancyPercent=45）时触发并发标记。
    //            使用特点：存储长期存活对象，采用标记-清除或标记-整理算法，混合回收（Mixed GC）时会选择性回收高垃圾比例的Region。
    // 堆使用、
    // 堆容量、    容量：由-Xmx（最大堆）和-Xms（初始堆）控制，建议两者相等以避免动态扩展的开销。
    //            使用监控：通过jstat -gc或jcmd GC.heap_info查看各区域实时使用量，如OC（老年代容量）、OU（老年代使用量）。
    // 原空间使用、 容量：使用本地内存，默认无上限，可通过-XX:MaxMetaspaceSize限制。与永久代不同，元空间动态扩展且减少OOM风险。
    // 内存回收、   年轻代：复制算法（Survivor区交替使用），高效但需预留50%空间。
    //            老年代：标记-清除（碎片风险）或标记-整理（无碎片但耗时），G1等收集器结合混合回收策略。
    // 对象晋升、   年龄阈值：默认15次Minor GC（-XX:MaxTenuringThreshold可调）。
    //            动态年龄判断：若某年龄段对象总大小超过Survivor区50%，则直接晋升。
    //            大对象直进：超过-XX:PretenureSizeThreshold（默认1MB）的对象直接进入老年代。
    // Young GC时长、  触发条件：Eden区满时触发，仅回收年轻代（含Survivor区）。
    //                时长：通常较短（毫秒级），但存活对象多时复制开销增加。例如，日志中367523K->1293K耗时0.002秒。
    // Mixed GC时长、  触发条件：G1中并发标记后，回收年轻代及部分老年代Region。
    //                时长：可控（通过-XX:MaxGCPauseMillis，默认200ms），但需权衡回收区域数量与停顿时间。
    // Full GC时长、   触发条件：老年代空间不足、元空间不足或显式调用System.gc()。
    //                时长：最长（秒级），需暂停所有线程，如日志中520K->408K耗时0.004秒。
    // Concurrent Mark Cycle时长、
    // Concurrent Mark时长、
    // Pause Remark时长、
    // Pause Cleanup时长        阶段与时长：
    //                         Concurrent Mark：与应用线程并行，耗时较长（秒级），标记存活对象。
    //                         Remark（Pause）：短暂暂停（毫秒级），修正标记结果。
    //                         Cleanup（Pause）：清理阶段，统计Region回收价值。


    // -------------------------3、线程快照-------------------------
    // 1、基础信息
    // 文件创建
    //2025-03-21 12:27:45
    //JVM信息
    //Java HotSpot(TM) 64-Bit Server VM (25.192-b12 mixed mode)
    //JNI 引用数
    //34219
    //死锁数量
    //0
    //线程数
    //1491
    //Java 线程数
    //1480
    //Java 守护线程数量
    //1449
    //GC 线程数
    //7
    //编译器线程数
    //2
    //虚拟机线程数
    //2

    // 2、文件内容
    // 2025-03-21 12:27:45
    //Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.192-b12 mixed mode):
    //
    //"Keep-Alive-Timer" #2644 daemon prio=8 os_prio=0 tid=0x00007f7d4001f000 nid=0x8b694 waiting on condition [0x00007f7ed04cf000]
    //   java.lang.Thread.State: TIMED_WAITING (sleeping)
    //	at java.lang.Thread.sleep(Native Method)
    //	at sun.net.www.http.KeepAliveCache.run(KeepAliveCache.java:172)
    //	at java.lang.Thread.run(Thread.java:748)
    //
    //"metrics-meter-tick-thread-2" #2640 daemon prio=5 os_prio=0 tid=0x00007f7d88023000 nid=0x88817 waiting on condition [0x00007f7e025e6000]
    //   java.lang.Thread.State: TIMED_WAITING (parking)
    //	at sun.misc.Unsafe.park(Native Method)
    //	- parking to wait for  <0x00000000c7d00318> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
    //	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
    //	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
    //	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
    //	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
    //	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
    //	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
    //	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
    //	at java.lang.Thread.run(Thread.java:748)


    // -------------------------4、火焰图-------------------------
    // CPU采样、内存采样、锁性能采样、Waiting线程采样
    // 1、CPU采样
    // 火焰图中的每一个方框是一个函数，方框的长度，代表了它的执行时间，所以越宽的函数，执行越久。火焰图的楼层每高一层，就是更深一级的函数被调用，最顶层的函数，是叶子函数。
    //
    //火焰图是基于 stack 信息生成的 SVG 图片, 用来展示 CPU 的调用栈。
    //y 轴表示调用栈, 每一层都是一个函数. 调用栈越深, 火焰就越高, 顶部就是正在执行的函数, 下方都是它的父函数.
    //x 轴表示抽样数, 如果一个函数在 x 轴占据的宽度越宽, 就表示它被抽到的次数多, 即执行的时间长. 注意, x 轴不代表时间, 而是所有的调用栈合并后, 按字母顺序排列的





}
