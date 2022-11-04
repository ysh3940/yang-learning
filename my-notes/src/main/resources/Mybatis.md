1、#{}和${}的区别是什么？<br>
#{}是预编译处理，${}是字符串替换。<br>
Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；<br>
Mybatis在处理${}时，就是把${}替换成变量的值。<br>

2、通常一个Xml映射文件，都会写一个Dao接口与之对应，请问，这个Dao接口的工作原理是什么？Dao接口里的方法，参数不同时，方法能重载吗？<br>
Mapper接口里的方法，是不能重载的，因为是使用 全限名+方法名 的保存和寻找策略。Mapper 接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Mapper接口生成代理对象proxy，代理对象会拦截接口方法，转而执行MapperStatement所代表的sql，然后将sql执行结果返回。<br>

3、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？<br>
Mybatis仅支持association关联对象和collection关联集合对象的延迟加载，association指的就是一对一，collection指的就是一对多查询。在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。<br>
它的原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用。这就是延迟加载的基本原理。<br>

4、Mybatis的一级、二级缓存<br>
数据查找过程：<br>
二级缓存（基于 mapper文件的namespace的、二级缓存SqlSessionFactory进行管理的。SqlSessionFactory对象是进程级别的。可以被多个SqlSession所共享，跟Web应用中application对象作用范围类似） -> <br>
一级缓存（一级缓存是SqlSession自带的。SqlSession对象被创建，一级缓存就存在了，如果SqlSession对象关闭或调用清理方法，会导致缓存失效） -> <br>
数据库<br>

5、二级缓存的应用场景<br>
对于访问多的查询请求且用户对查询结果实时性要求不高，此时可采用mybatis二级缓存技术降低数据库访问量，提高访问速度，业务场景比如：耗时较高的统计分析sql等<br>
实现方法如下：通过设置刷新间隔时间，由mybatis每隔一段时间自动清空缓存，根据数据变化频率设置缓存刷新间隔flushInterval，比如设置为30分钟、60分钟、24小时等，根据需求而定。 <br>

6、一二级缓存在分布式环境下的问题，查询脏数据<br>
一级缓存：<select id="refreshLevelOneCache" flushCache="true" resultType="java.lang.Integer">刷新缓存<br>
mybatis设置了一级缓存，在同一个事务中，读取完一次数据后，下次会从缓存中读取，不读数据库。此时，不论数据库隔离级别如何都不会产生影响。（没有禁用一级缓存）<br>
如果没有事务，那么每次读取，都会去获取一个sqlsession，而不同sqlsession的一级缓存不共享，每次查询都要读库，所以总是能读到被更新后的数据。（没有禁用一级缓存）<br>
二级缓存：使用redis实现一套mybatis二级缓存插件，目前mybatis社区开放了mybatis-redis项目<br>

下面文章讲的很不错（MVCC）
关于innodb下，幻读是如何被解决的：https://juejin.im/post/5c7912eee51d4547222f5d3c
