1、怎么保证数据一致<br>
C：Zookeeper保证了最终一致性,在十几秒可以Sync到各个节点.<br>
A：Zookeeper保证了可用性,数据总是可用的,没有锁.并且有一大半的节点所拥有的数据是的,实时的. 如果想保证取得是数据一定是的,需要手工调用Sync()<br>
P：① 节点多了会导致写数据延时非常大,因为需要多个节点同步.② 节点多了Leader选举非常耗时, 就会放大网络的问题. 可以通过引入 observer（不参与选举）节点缓解这个问题.<br>
Commit过的数据不丢失、未Commit过的消息对客户端不可见<br>

2、节点3种角色<br>
leader:处理所有请求，为客户的提供读和写服务<br>
follower:只提供读服务，有机会通过选举成为leader<br>
observer:只提供读服务 <br>

3、Zab协议<br>
广播模式：广播模式类似一个简单的两阶段提交，广播协议在所有的通讯过程中使用TCP的FIFO信道，通过使用该信道，使保持有序性变得非常的容易。通过FIFO信道，消息被有序的deliver。只要收到的消息一被处理，其顺序就会被保存下来。<br>
恢复模式：Leader故障，恢复过程中必须选举出一个新Leader，且大多数server完成了和leader的状态同步以后，恢复模式就结束了。状态同步保证了leader和server具有相同的系统状态。<br>

4、zxid<br>
高32位epoch，低32位counter，每当Leader发生变换时，epoch位就加1，counter位置0。<br>

5、写操作<br>
客户端向Leader发起写请求<br>
Leader将写请求以Proposal的形式发给所有Follower并等待ACK<br>
Follower收到Leader的Proposal后返回ACK<br>
Leader得到过半数的ACK（Leader对自己默认有一个ACK）后向所有的Follower和Observer发送Commmit<br>
Leader将处理结果返回给客户端<br>
这里要注意：<br>
Leader并不需要得到Observer的ACK，即Observer无投票权<br>
Leader不需要得到所有Follower的ACK，只要收到过半的ACK即可，同时Leader本身对自己有一个ACK。上图中有4个Follower，只需其中两个返回ACK即可，因为(2+1) / (4+1) > 1/2<br>
Observer虽然无投票权，但仍须同步Leader的数据从而在处理读请求时可以返回尽可能新的数据<br>

6、投票流程<br>
logicClock：每个服务器会维护一个自增的整数，名为logicClock，它表示这是该服务器发起的第多少轮投票<br>
state：当前服务器的状态<br>
self_id：当前服务器的myid<br>
self_zxid：当前服务器上所保存的数据的最大zxid<br>
vote_id：被推举的服务器的myid<br>
vote_zxid：被推举的服务器上所保存的数据的最大zxid<br>
发送初始化选票：每个服务器最开始都是通过广播把票投给自己。<br>
接收外部投票<br>
判断选举轮次：收到外部投票后，首先会根据投票信息中所包含的logicClock来进行不同处理<br>
选票PK<br>
统计选票<br>
更新服务器状态：投票终止后，服务器开始更新自身状态。若过半的票投给了自己，则将自己的服务器状态更新为LEADING，否则将自己的状态更新为FOLLOWING<br>



zk实现分布式锁：https://juejin.im/post/5c01532ef265da61362232ed

