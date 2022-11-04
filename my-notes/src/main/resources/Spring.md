1、spring事物怎么实现的（编程式和声明式）<br>
1.自动提交事务：每执行一条sql语句，就同步到数据库中。 <br>
2.手动提交事务：执行一系列的sql语句后一起同步到数据库中。<br>
编程式事务管理使用TransactionTemplate。<br>
声明式事务管理建立在AOP之上的。其本质是通过AOP功能，对方法前后进行拦截，将事务处理的功能编织到拦截的方法中，也就是在目标方法开始之前加入一个事务，在执行完目标方法之后根据执行情况提交或者回滚事务。<br>
声明式事务最大的优点就是不需要在业务逻辑代码中掺杂事务管理的代码，只需在配置文件中做相关的事务规则声明或通过@Transactional注解的方式，便可以将事务规则应用到业务逻辑中。<br>
声明式事务管理要优于编程式事务管理，这正是spring倡导的非侵入式的开发方式，使业务代码不受污染，只要加上注解就可以获得完全的事务支持。唯一不足地方是，最细粒度只能作用到方法级别，无法做到像编程式事务那样可以作用到代码块级别。<br>


ACID特性：
1、原子性（atomacity）
整个事务中的所有操作，要么全部成功，要么全部失败，任意一个失败都会导致整个事务回滚，不会出现部分执行成功部分执行失败的情况。
2、一致性（consistency）
数据库总是从一个一致性状态转到另外一个一致性状态，事务结束后系统数据状态是一致的。
3、隔离性（isolation）
在并发执行时，一个事务对另外一个事务是彼此独立的，事务之间能否相互看到数据状态，是我们需要讨论的隔离级别问题。
4、持久性（durability）
事务一旦提交，数据永久保存在数据库中。

Spring事务失效的2种情况：
1、使用默认的事务处理方式
　　因为在java的设计中，它认为不继承RuntimeException的异常是”checkException”或普通异常，如IOException，这些异常在java语法中是要求强制处理的。对于这些普通异常，spring默认它们都已经处理，所以默认不回滚。可以添加rollbackfor=Exception.class来表示所有的Exception都回滚。
@Transactional(rollbackFor = Exception.class)：为什么要这么写？
rollbackFor默认值为UncheckedException，包括了RuntimeException和Error.
当我们直接使用@Transactional不指定rollbackFor时，Exception及其子类都不会触发回滚。

2、内部调用（https://blog.csdn.net/yhl_jxy/article/details/86539400）



2、spring的事务传播行为<br>
支持当前事务的情况：
TransactionDefinition.PROPAGATION_REQUIRED： 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
TransactionDefinition.PROPAGATION_SUPPORTS： 如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
TransactionDefinition.PROPAGATION_MANDATORY： 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。（mandatory：强制性）

不支持当前事务的情况：
TransactionDefinition.PROPAGATION_REQUIRES_NEW： 创建一个新的事务，如果当前存在事务，则把当前事务挂起。
TransactionDefinition.PROPAGATION_NOT_SUPPORTED： 以非事务方式运行，如果当前存在事务，则把当前事务挂起。
TransactionDefinition.PROPAGATION_NEVER： 以非事务方式运行，如果当前存在事务，则抛出异常。

其他情况：
TransactionDefinition.PROPAGATION_NESTED： 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。


3、Spring中的隔离级别<br>
TransactionDefinition.ISOLATION_DEFAULT:	使用后端数据库默认的隔离级别，Mysql 默认采用的 REPEATABLE_READ隔离级别 Oracle 默认采用的 READ_COMMITTED隔离级别.
TransactionDefinition.ISOLATION_READ_UNCOMMITTED: 最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读
TransactionDefinition.ISOLATION_READ_COMMITTED: 	允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生
TransactionDefinition.ISOLATION_REPEATABLE_READ: 	对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。
TransactionDefinition.ISOLATION_SERIALIZABLE: 	最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。


4、Spring AOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理<br>
①JDK动态代理只提供接口的代理，不支持类的代理。核心InvocationHandler接口和Proxy类，InvocationHandler通过invoke()方法反射来调用目标类中的代码，动态地将横切逻辑和业务编织在一起；接着，Proxy利用 InvocationHandler动态创建一个符合某一接口的的实例,生成目标类的代理对象。<br>
②如果代理类没有实现接口，那么Spring AOP会选择使用CGLIB来动态代理目标类。CGLIB（Code Generation Library），是一个代码生成的类库，可以在运行时动态的生成指定类的一个子类对象，并覆盖其中特定方法并添加增强代码，从而实现AOP。CGLIB是通过继承的方式做的动态代理，因此如果某个类被标记为final，那么它是无法使用CGLIB做动态代理的。<br>

5、请解释Spring Bean的生命周期<br>
首先说一下Servlet的生命周期：实例化，初始init，接收请求service，销毁destroy；<br>
 Spring上下文中的Bean生命周期也类似，如下：<br>
（1）实例化Bean：对于BeanFactory容器，当客户向容器请求一个尚未初始化的bean时，或初始化bean的时候需要注入另一个尚未初始化的依赖时，容器就会调用createBean进行实例化。对于ApplicationContext容器，当容器启动结束后，通过获取BeanDefinition对象中的信息，实例化所有的bean。<br>
（2）设置对象属性（依赖注入）：实例化后的对象被封装在BeanWrapper对象中，紧接着，Spring根据BeanDefinition中的信息 以及 通过BeanWrapper提供的设置属性的接口完成依赖注入。<br>
（3）处理Aware接口：接着，Spring会检测该对象是否实现了xxxAware接口，并将相关的xxxAware实例注入给Bean：<br>
①如果这个Bean已经实现了BeanNameAware接口，会调用它实现的setBeanName(String beanId)方法，此处传递的就是Spring配置文件中Bean的id值；<br>
②如果这个Bean已经实现了BeanFactoryAware接口，会调用它实现的setBeanFactory()方法，传递的是Spring工厂自身。<br>
③如果这个Bean已经实现了ApplicationContextAware接口，会调用setApplicationContext(ApplicationContext)方法，传入Spring上下文；<br>
（4）BeanPostProcessor：如果想对Bean进行一些自定义的处理，那么可以让Bean实现了BeanPostProcessor接口，那将会调用postProcessBeforeInitialization(Object obj, String s)方法。由于这个方法是在Bean初始化结束时调用的，所以可以被应用于内存或缓存技术；<br>
（5）InitializingBean 与 init-method：如果Bean在Spring配置文件中配置了 init-method 属性，则会自动调用其配置的初始化方法。<br>
（6）如果这个Bean实现了BeanPostProcessor接口，将会调用postProcessAfterInitialization(Object obj, String s)方法；以上几个步骤完成后，Bean就已经被正确创建了，之后就可以使用这个Bean了。<br>
（7）DisposableBean：当Bean不再需要时，会经过清理阶段，如果Bean实现了DisposableBean这个接口，会调用其实现的destroy()方法；<br>
（8）destroy-method：最后，如果这个Bean的Spring配置中配置了destroy-method属性，会自动调用其配置的销毁方法。<br>

6、Spring 框架中都用到了哪些设计模式<br>
（1）工厂模式：BeanFactory就是简单工厂模式的体现，用来创建对象的实例；<br>
（2）单例模式：Bean默认为单例模式。<br>
（3）代理模式：Spring的AOP功能用到了JDK的动态代理和CGLIB字节码生成技术；<br>
（4）模板方法：用来解决代码重复的问题。比如. RestTemplate, JmsTemplate, JpaTemplate。<br>
（5）观察者模式：定义对象键一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都会得到通知被制动更新，如Spring中listener的实现--ApplicationListener。<br>

7、一个spring mvc的请求流程<br>
具体步骤：<br>
第一步：发起请求到前端控制器(DispatcherServlet)<br>
第二步：前端控制器请求HandlerMapping查找 Handler （可以根据xml配置、注解进行查找）<br>
第三步：处理器映射器HandlerMapping向前端控制器返回Handler，HandlerMapping会把请求映射为HandlerExecutionChain对象（包含一个Handler处理器（页面控制器）对象，多个HandlerInterceptor拦截器对象），通过这种策略模式，很容易添加新的映射策略<br>
第四步：前端控制器调用处理器适配器去执行Handler<br>
第五步：处理器适配器HandlerAdapter将会根据适配的结果去执行Handler<br>
第六步：Handler执行完成给适配器返回ModelAndView<br>
第七步：处理器适配器向前端控制器返回ModelAndView （ModelAndView是springmvc框架的一个底层对象，包括 Model和view）<br>
第八步：前端控制器请求视图解析器去进行视图解析 （根据逻辑视图名解析成真正的视图(jsp)），通过这种策略很容易更换其他视图技术，只需要更改视图解析器即可<br>
第九步：视图解析器向前端控制器返回View<br>
第十步：前端控制器进行视图渲染 （视图渲染将模型数据(在ModelAndView对象中)填充到request域）<br>
第十一步：前端控制器向用户响应结果<br>

10、spring 的 controller 是单例还是多例，怎么保证并发的安全。<br>
单例 :通过单例工厂 DefaultSingletonBeanRegistry实现单例 ，需要在类上添加注解@Scope("prototype")即可，这样每次请求调用的类都是重新生成的（每次生成会影响效率）。<br>

11、用 Spring 的原因是什么？<br>
想想你会发现原来 Spring 解决了一个非常关键的问题他可以让你把对象之间的依赖关系转而用配置文件来管理，也就是他的依赖注入机制。而这个注入关系在一个叫 Ioc 容器中管理，那 Ioc 容器就是被 Bean 包裹的对象。Spring 正是通过把对象包装在 Bean 中而达到对这些对象的管理以及一些列额外操作的目的。<br>
Bean 包装的是 Object，而 Object 必然有数据，如何给这些数据提供生存环境就是 Context 要解决的问题，对 Context 来说他就是要发现每个 Bean 之间的关系，为它们建立这种关系并且要维护好这种关系。所以 Context 就是一个 Bean 关系的集合，这个关系集合又叫 Ioc 容器，一旦建立起这个 Ioc 容器后 Spring 就可以为你工作了。那 Core 组件又有什么用武之地呢？其实 Core 就是发现、建立和维护每个 Bean 之间的关系所需要的一些列的工具，从这个角度看来，Core 这个组件叫 Util 更能让你理解。<br>
Bean 组件在 Spring 的 org.springframework.beans 包下。这个包下的所有类主要解决了三件事：Bean 的定义、Bean 的创建以及对 Bean 的解析。<br>
Context 在 Spring 的 org.springframework.context 包下，前面已经讲解了 Context 组件在 Spring 中的作用，他实际上就是给 Spring 提供一个运行时的环境，用以保存各个对象的状态。<br>
Core 组件作为 Spring 的核心组件，他其中包含了很多的关键类，其中一个重要组成部分就是定义了资源的访问方式。<br>

