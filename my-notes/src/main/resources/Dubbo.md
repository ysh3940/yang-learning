1、使用dubbo的时候遇到的问题<br>
api设计的时候，参数使用了enum，如果enum升级了，服务端没升级api，调用端使用了新的enum，调用会报错<br>
api的pojo类一定要实现序列化接口，不然调用会报错<br>

2、dubbo的一次调用流程<br>
service 接口定义实现层：用户定义api，实现api<br>
config 配置层：对外配置接口<br>
proxy 服务代理层：服务接口透明代理，生成服务的客户端 Stub 和服务器端 Skeleton<br>
registry 注册中心层：封装服务地址的注册与发现，以服务 URL 为中心<br>
cluster 路由层：封装多个提供者的路由及负载均衡，并桥接注册中心<br>
monitor 监控层：RPC 调用次数和调用时间监控，以 Statistics 为中心<br>
protocol 远程调用层：封装 RPC 调用，以 Invocation, Result 为中心<br>
exchange 信息交换层：封装请求响应模式，同步转异步，以 Request, Response 为中心<br>
transport 网络传输层：抽象 mina 和 netty 为统一接口，以 Message 为中心<br>
serialize 数据序列化层：可复用的一些工具<br>

3、dubbo 支持不同的通信协议<br>
dubbo 协议：单一长连接，进行的是 NIO 异步通信，基于 hessian 作为序列化协议。使用的场景是：传输数据量小（每次请求在 100kb 以内），但是并发量很高。<br>
rmi 协议：走 Java 二进制序列化，多个短连接，适合消费者和提供者数量差不多的情况，适用于文件的传输，一般较少用。<br>
hessian 协议：走 hessian 序列化协议，多个短连接，适用于提供者数量比消费者数量还多的情况，适用于文件的传输，一般较少用。<br>
http 协议：走 json 序列化。<br>
webservice：走 SOAP 文本序列化。<br>

4、dubbo 支持的序列化协议<br>
dubbo 支持 hession、Java 二进制序列化、json、SOAP 文本序列化多种序列化协议。但是 hessian 是其默认的序列化协议。<br>

5、dubbo 负载均衡策略<br>
random loadbalance：按照权重来负载均衡<br>
roundrobin loadbalance：就是均匀地将流量打到各个机器上去<br>
leastactive loadbalance：这个就是自动感知一下，如果某个机器性能越差，那么接收的请求越少，越不活跃，此时就会给不活跃的性能差的机器更少的请求<br>
consistanthash loadbalance：一致性 Hash 算法，相同参数的请求一定分发到一个 provider 上去<br>

6、dubbo 集群容错策略<br>
failover cluster 模式：失败自动切换，自动重试其他机器，常见于读操作<br>
failfast cluster模式：一次调用失败就立即失败，常见于写操作<br>
failsafe cluster 模式：出现异常时忽略掉，常用于不重要的接口调用，比如记录日志<br>
failback cluster 模式：失败了后台自动记录请求，然后定时重发，比较适合于写消息队列这种<br>
forking cluster 模式：并行调用多个 provider，只要一个成功就立即返回<br>
broadcacst cluster：逐个调用所有的 provider<br>

7、dubbo动态代理策略<br>
默认使用 javassist 动态字节码生成，创建代理类。但是可以通过 spi 扩展机制配置自己的动态代理策略<br>

8、服务治理<br>
1. 调用链路生成<br>
2. 服务访问压力以及时长统计<br>
3. 其它：服务分层（避免循环依赖）、调用链路失败监控和报警、服务鉴权、每个服务的可用性的监控（接口调用成功率）<br>

9、服务降级<br>
HelloService我们调用接口失败的时候，可以通过 mock 统一返回 null，mock 的值也可以修改为 true，然后再跟接口同一个路径下实现一个 Mock 类，命名规则是 “接口名称+Mock” 后缀。然后在 Mock 类里实现自己的降级逻辑<br>

10、失败重试和超时重试<br>
<dubbo:reference id="xxxx" interface="xx" check="true" async="false" retries="3" timeout="2000"/><br>

11、如何自己设计一个类似 Dubbo 的 RPC 框架？<br>
RPC框架三个核心角色(1服务提供者（Server）、2注册中心（Registry）、3服务消费者（Client）)<br>
1）服务调用方（client）调用以本地调用方式调用服务；<br>
2）client stub接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体；在Java里就是序列化的过程<br>
3）client stub找到服务地址，并将消息通过网络发送到服务端；<br>
4）server stub收到消息后进行解码,在Java里就是反序列化的过程；<br>
5）server stub根据解码结果调用本地的服务；<br>
6）本地服务执行处理逻辑；<br>
7）本地服务将结果返回给server stub；<br>
8）server stub将返回结果打包成消息，Java里的序列化；<br>
9）server stub将打包后的消息通过网络并发送至消费方<br>
10）client stub接收到消息，并进行解码, Java里的反序列化；<br>
11）服务调用方（client）得到最终结果。<br>
RPC框架的目标就是要2~10这些步骤都封装起来。<br>

12、一次 RPC 请求的流程是什么。<br>
   1）服务消费方（client）调用以本地调用方式调用服务； <br>
   2）client stub接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体； <br>
   3）client stub找到服务地址，并将消息发送到服务端； <br>
   4）server stub收到消息后进行解码； <br>
   5）server stub根据解码结果调用本地的服务； <br>
   6）本地服务执行并将结果返回给server stub； <br>
   7）server stub将返回结果打包成消息并发送至消费方； <br>
   8）client stub接收到消息，并进行解码； <br>
   9）服务消费方得到最终结果。<br>

