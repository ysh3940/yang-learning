1、RabbitMQ怎么保证消息一定发送成功<br>
对发送消息的代码try-catch下，发生异常把消息记录到数据库，由定时任务执行重新发送消息<br>
如果不记录mysql，可以使用RabbitMQ异步Confirm模式，把消息id和消息body保存在内存中，在ConfirmListener的失败方法中重发消息<br>

2、RabbitMQ怎么保证消息一定消费成功<br>
消息消费使用默认配置(自动ack)，如果消费异常，未捕获的情况下，一直在重复消费直到消费成功（如果代码一直异常，会导致后续的消息一直不能被消费）<br>
可以把消费异常的消息记录到mysql，由定时任务去重新消费消息<br>

3、RabbitMQ怎么实现广播消息<br>
RabbitMQ的Fanout模式，应该知道它原本的Fanout模式就是用来做广播的。但是它的广播有一点区别，来回顾下它的含义：Fanout类型没有路由键的概念，只要队列绑定到了改exchange上面，就会接收到所有的消息。<br>
方式1：通过redis的发布订阅模式来通知其他兄弟节点<br>
方式2：exchange绑定队列1，2，3，服务实例1，2，3在本地配置下监听的队列对应是q1，q2，q3<br>

4、用过哪些 MQ，和其他 mq 比较有什么优缺点，MQ 的连接是线程安全的吗，你们公司的MQ 服务架构怎样的。

5、rabbitmq 如何实现集群高可用。<br>
RabbitMQ集群结构：普通模式(默认)、镜像模式(基于镜像队列)<br>
普通模式(默认)：由3个节点(Node1,Node2,Node3)组成的RabbitMQ普通集群环境，Exchange A的元数据信息在所有节点上是一致的;而Queue的完整信息只有在创建它的节点上，各个节点仅有相同的元数据，即队列结构。当producer发送消息到Node1节点的Queue1中后，consumer从Node3节点拉取时，RabbitMQ会临时在Node1、Node3间进行消息传输，把Node1中的消息实体取出并经过Node3发送给consumer。该模式存在一个问题：当Node1节点发生故障后，Node3节点无法取到Node1节点中还未被消费的消息实体。如果消息没做持久化，那么消息将永久性丢失;如果做了持久化，那么只有等Node1节点故障恢复后，消息才能被其他节点消费。<br>
镜像模式(基于镜像队列)：在普通模式的基础上，把需要的队列做成镜像队列，存在于多个节点来实现高可用(HA)。该模式解决了上述问题，Broker会主动地将消息实体在各镜像节点间同步，在consumer取数据时无需临时拉取。该模式带来的副作用也很明显，除了降低系统性能外，如果镜像队列数量过多，加之大量的消息进入，集群内部的网络带宽将会被大量消耗。通常地，对可靠性要求较高的场景建议采用镜像模式。<br>


Java服务MQ消息队列容灾方案：http://www.bnee.net/article/1895618.html
mq异步发送方案：
1、初始化线程池：核心数量=2*CPU核心，最大数量=2*核心数量，队里使用ArrayBlockQueue=1000（超出1000的任务，使用自定义handler写入mysql，后台定时任务重新提交到线程池，其中需要监控线程池状态，以及并发处理同一条记录的情况）
如果重新提交线程实现比较复杂，可以直接进入步骤2，直接写入发送mq失败的日志表
2、使用线程池发送，try catch，出现异常的情况下，把消息写入mysql，后台定时任务处理（需要注意高并发的情况，处理同一条消息）
处理失败消息的流程：1、先根据id获取redis锁 2、先开启mysql事物，更新消息状态为发送成功 3、执行mq发送逻辑（如果发送失败事物回滚）


RabbitMQ集群架构全解：https://juejin.im/post/5b586b125188257bcb59005e

