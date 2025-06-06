package cn.yang.learning.notes.note2025项目;

public class jianli笔记 {

    //-----------------------------------------------------------------------------------------------
    // 1、营销提报系统开发：主导京东营销工具平台提报系统招商报名核心功能开发，支撑70W+商家报名，活动期间峰值QPS1000+，成功保障大促618/双11大促活动平稳运行。
    // -京麦首页访问，大促事业群营销活动+百补+秒杀+开放化（便宜包邮，plus95折等）+综促活动+会场活动
    // shop日常：getVenderId（qpm=1万上下，qps=100-200）
    // isSignAgreement4Calender（qpm=7.5万上下，qps=1250）（最高10万+分钟，qps=1666）（日常3万，qps=500）
    // isSignAgreementV2（qpm = 130）
    // getActivitySalesHelperV2（qpm = 140）（双11 qpm=540）
    // queryForApplyList（qpm=140）（双11=570）
    // applyGoods（qpm=51800，qps=863）（双11 qpm=150000，qps=2500）
    // calenderPageInfoList商家首页活动(日常 qpm=14000，qps=233)（日常 qpm=5000， qps=100左右）
    // getCalenderPlanCodeInfo商家首页活动详情（日常 5qpm=2000）
    // 核心功能：1、离线任务加工选品结果表数据发送mq，消费mq写入es集群，2、整个报名流程到数据写入mysql以及执行打标，加券，3、数据同步任务开发，同步全量打标，同步全量加券
    // 4、标券的黑白名单功能设计开发

    // 2、复杂需求落地能力：独立完成多个核心营销活动模块迭代，需求交付准时率100%。
    // -自动报名功能，价格校验功能，供应商角色商家运营角色代报名功能等
    // 价格校验的功能经过多个迭代已经做成通用化的组件能力（场次配置，报名数据需要提供离线表+当天流水表，业务方对接结果mq去做实际业务处理），新业务可快速接入

    // 3、跨团队协同：联动产品、测试、业务，推动平台营销工具能力升级，还有建立跨团队协作机制，优化需求评审与测试流程。
    // -对于复杂需求我们会提前拉开发负责人+测试负责人和产品会提前进行小范围的评审沟通，进入开发阶段后分批功能进行提测，提高整体项目效率

    // 4、抗压与快速响应：连续4年保障618/双11大促系统稳定性，紧急问题修复平均时效<30分钟，搭建实时监控与预警系统，及时发现系统问题。
    // -每次大促期间都会准备常见问题对应的执行预案，1、比如商家价格调整后，价格校验没有执行，没有恢复上标上券
    //  2、大促期间业务经常会上传大量黑名单名单执行加减券，经常会有文件格式不正确导致解析异常，我们就需要执行预案关注异常告警同步业务
    //  3、大促晚8期间，会有上亿sku触发价格变更，mq无法在短时间内消费完成，针对紧急商家业务会把他们名下的全量sku执行价格校验走特殊快速处理通道
    //  在晚8前，我们会执行预案把有动销的sku全部扫出来发送到mq（特殊通道），等晚8一到开始消费进行价格校验


    //-----------------------------------------------------------------------------------------------
    // 1 、主导京东零售营销提报系统0到1架构设计 ：从零开始搭建提报营销平台 ，完成技术选型与架构设计 ，支撑70W+商家和采销参 与报名营销活动 ，提升提报流程效率 ，实现活动创建报名全流程线上化操作。
    // 技术选型：活动基础信息等使用mysql单库存储，可报数据使用es集群存储，已报数据使用mysql分库存储，使用spark去加工选品结果离线表发送mq异步写入es集群
    // 架构设计：网关层代码和核心业务代码分开部署，http层独立部署一个网关应用（异地多机房部署）（商家端+运营端），核心业务代码也独立部署一个应用（异地多机房部署）（底层代码同时支持商家端+运营端）
    // http层：异地多机房部署（独立应用）
    // 业务层：异地多机房部署（独立应用）（不同机房设置不同别名，http应用按机房配置调用别名）
    // 存储层：es多集群，活动根据路由规则（活动级别在平台大促级的路由重保es集群，事业群活动均衡的存储到其他5个普通es集群）存储到不同集群
    // mysql分库分表：按报名人纬度分片，同一个报名人的数据存储到同一个表（报名人表只分库64，报名sku表分库分表64x256）
    // 缓存层：使用Redis集群去抵御商家端首页高频访问的报名中的活动信息，商家是否可报状态，可报已报数量，商家签约信息等
    // 活动列表（商家端首页展示有效活动，活动创建成功后加入到zSet，分数=活动结束时间，不设过期时间，定时任务每天清理已结束的活动）,缓存查不到促发兜底逻辑从es查询有效活动
    //        zSet:跳跃表（skiplist）和哈希表（hash table）
    //        String key = "有效活动列表_shop";
    //        redisClient.zAdd(key, 111, "活动1json");// 111 是活动结束时间
    //        redisClient.zAdd(key, 222, "活动2json");
    //        redisClient.zAdd(key, 333, "活动3json");
    //        // 所有未结束的活动
    //        redisClient.zRangeByScore(key, System.currentTimeMillis(), Long.MAX_VALUE);
    // 单个活动信息（创建修改同步更新缓存）
    // 商家签约信息（查询es构建缓存）
    // 可报已报数量-商家首页（方法1：查询es构建缓存，方法2：1、缓存不存在时返回0，2、报名或退出mq促发统计数量-过期时间活动结束时间+随机几分钟）

    // 2 、主导提报平台核心模块开发 、性能优化 ，承载618/双11等大促活动报名 ，通过Redis缓存本地缓存优化 ，QPS峰值提升至 1000+。
    // 核心模块：离线数据加工mq写es，自动报名，报名模块支持多角色（采销助理、自营供应商、类目运营）
    // 性能优化：1、单es集群升级到6个es集群，提升可保数据写入性能，可保数据查询性能 2、面向商家端首页的接口优化成查询缓存减少数据库查询
    //  3、自动报名原先扫出报名人发mq，消费单报名人扫出sku发送mq（线上问题，消费单个报名人的时候有些人的sku数量操过100万，导致mq消费超时-默认2分钟-重新触发消费，最终导致es单节点cpu100告警）
    //  不好的设计点：报名人mq限流开大了mq告警，超时时间也不能设置的很大，优化成 报名人写到异步任务表，通过定时任务触发扫sku
    //  4、价格校验白名单判断使用线程池并行
    //  5、标的价格校验和券的价格校验队列，不同业务使用不同队列，减少因积压影响其他业务
    //  6、价格校验针对热点redis的key，加入本地缓存（查询本地缓存不存在，callable加载数据，其他线程阻塞）
    //        userInfoCache.get(erp, new Callable<UserInfo>() {
    //            @Override
    //            public UserInfo call() throws Exception {
    //                return JSONObject.parseObject(redisClient.get(userInfoKey),UserInfo.class);
    //            }
    //        });
    //  7、价格校验部分机器cpu100优化，通过火焰图，最后定位写log的方法 Thread.currentThread().getStackTrace() 获取堆栈信息，在qps高的很耗cpu

    // 3 、主导设计开发营销活动Worker任务组件 ，支持单营销活动1亿+SKU数据处理 ，采用spark+mq+redis+本地缓存等技术 ，sku处 理速度峰值提升至10W+。
    // 整体架构：1、校验场次资质配置（对于开启的活动，需要构建sku已报缓存） 2、离线定时任务读取已报数据，spark快速读取hive表并发出mq
    //   3、业务端消费mq（1、前置校验活动信息，场次信息 2、过滤非已报sku 3、查询sku实时价 4、调用资质校验sku实时价是否小于N天价 5、查询sku的当前标状态+查新sku当前的设券状态 ）
    //   4、校验完成发送结果mq（前置校验是否已打已设券）

    // 4 、主导核心模块代码优化 ，沉淀通用能力组件 ，提升系统开发效率 ，制定代码评审规范。
    //   1、redis缓存key和val的字段名简化，减少存储
    //   2、自动报名任务执行优化，通过把报名人数据写到mysql通过定时任务异步去触发扫描sku的任务，减少线上消费报名人mq超时导致一直无限重试es的cpu100的问题
    //   3、es支持多集群优化，提升写入查询性能
    //   4、自动报名扫描出报名人的sku后，不是单独发到一个mq去消费，申请8个topic，把报名人取哈希值路由到8个topic去处理（防止同一个人下的sku量太大，某个时间点一直往一个节点写数据，
    //            防止单节点cpu高，以及提升消费速度）
    //   5、价格校验能力通用化（场次配置，数据读取、校验逻辑，支持快速接入新业务线上）
    //   6、开发前还有方案设计、方案评审，通过进入代码开发阶段，代码开发联调完成，提测的同时就开始组内代码评审，（以前是上线前需要代码评审，这样导致上线前还会改代码需要回归再测试）


    //-----------------------------------------------------------------------------------------------
    // 营销活动提报系统     架构设计开发                                                                                                                  2021.03-至今
    // 系统定位 ：面向京东采销 、商家及供应商的营销活动全生命周期管理平台 ，支持活动创建 、SKU报名 、数据监控等核心流程 。
    // 技术架构：基于 Spring Boot微服务架构（http和业务层代码分2个独立项目分开部署） ，采用异地多分区部署（每个应用部署2套不同的机房） ，支撑每秒1000+商家采销并发访问。
    // 核心组件 ：Es ：实现活动可报名数据的实时检索，JMQ ：异步解耦SKU同步（离线任务加工选品结果发mq，系统消费mq写es） 、状态通知等高吞吐场景 ，
    //          es部署6套集群，每套集群27节点（3master+18数据节点+6协调节点）（16C+物理32G（data16G，协调24G，master12G）+1000-1500G）,每个索引16个分片
    //          es写优化：1、data和协调节点分离部署 2、使用批量写提交 3、mq开启并行消费 4、时刻关注es告警
    // Redis + 本地缓存：构建多 级缓存体系 ，抵御瞬时流量冲击 ，MySQL分库分表：按报名人ID哈希分片 ，承载亿级报名数据 ，
    //      1、缓存主要用在商家端首页的接口
    //      2、jed按pin分，同一个人的sku都会写入同一个库同一个分片（同一批报名的sku可以使用批量写mysql）（如果按sku纬度分，同一个批量报过来的sku会分到不同的库，无法使用单独的本地事物）
    //         按pin分，就天然支持报名人纬度的已报列表数据查询
    //         写库成功后，发送mq，异步去执行打标设券操作，消费异常写入重试任务表进入重试
    //         需要注意的问题：1、热点分片（单个pin量大的场景，在自动报名的过程已经分多topic处理单topic都有限流）
    //                      2、扩容问题：64*256已经能支持上千个大促活动，加上历史活动归档，当前不需要考虑数据扩容问题
    //                      3、跨分库的事物问题：报名场景都是按人纬度操作的，避免跨库的操作（优先保证单独内的事物）
    //                      4、扩库最好X2 扩容
    //                      5、分表的唯一id怎么生成的（表的id还是使用的数据库自增id，每个表都加了报名id字段=活动id@pin@sku）
    // Hive离线数仓 ：沉淀历史活动数 据 ，支持活动数据分析报表生成。
    //
    // 项目业绩：
    // 1 、选品结果数据稳定同步：JMQ限流机制 ，根据ES集群负载调节SKU同步速率 ，保障大促期间ES集群0宕机。
    //         1、报名成功后，（打标设券成功后，都是发q去更新es）需要同步更新es的中的报名状态，打标状态，设券状态（涉及大批量Update es操作）
    //         2、报名成功立马退出，需要保证es的准确性 （1、使用mq顺序消息保证先后顺序 2、报名消息体内带时间戳，早于es库的报名状态修改时间则废弃）
    //         3、mysql和es怎么保证2边数据一致（mq异步更新es，异常记录日志表进入重试）
    // 2 、瞬时流量削峰：构建本地缓存+Redis缓存体系 ，降低数据库查询压力。
    //         1、商家端首页，查询有效活动、商家签约状态、商家已报可报数据等流量较大需要构建缓存去扛量
    // 3 、优化ES索引管理 ：实现按活动维度动态创建ES索引 ，采用 yx_sku_活动ID命名规则 ，累计创建2000+独立索引 ，针对大促核心 活动自动路由到重保高性能ES集群（ 27个节点16C32G ，数据节点1T ，
    // 总共申请6套ES集群） ，通过清理过期活动数据 ，减少es数据存储。
    //         1、es多套集群，不同业务的活动分散到不同集群，减少相互之间的影响，提升写入、查询速度
    //         2、单集群内按活动纬度创建索引，避免单索引内存在大量数据、不同活动不同方便数据归档、活动存在不同索引单个索引故障不会影响到其他活动
    //            不好地方：索引过多导致分片数增加（归档过期的活动）、无法夸活动查询（比如查pin报名了多少个活动）
    // 4 、海量已报名数据存储：采用MySQL分库分表（ 64库×256表） ，支撑单活动1亿+SKU报名数据存储 ，设计异步归档机制 ，将过 期数据迁移至Hive ，减少主库数据量。
    //         1、通过裂变报名人分片键去并发扫描sku数据执行归档（通过活动code扫描出所有报名人，写入异步任务表，通过定时任务并发去执行单个报名人的扫描任务）
    // 技术亮点：JMQ异步削峰 、多级缓存穿透防护 ，弹性扩展 ：ES动态索引 、MySQL分库分表 ，冷热数据分级存储 、离线归档历史活 动 ，保障大促期间618/双11活动平稳运行。



    //-----------------------------------------------------------------------------------------------
    // 营销活动价格校验worker系统     架构设计开发                                                                                           2022.08-至今
    // 技术架构 ：Spark + Hive + JMQ + Redis
    // 项目目标 ：实现单活动超1亿SKU的实时价格校验 ，满足大促期间1小时5000W数据的高吞吐处理需求。
    //         1、项目特点：数据量大，需要快递读取，需要构建大量缓存（key=活动id+sku，value=是否报名+sku基础信息）
    //         2、处理时效要求高，1亿数据要求1-2小时处理完成
    //
    // 项目方案：
    // 1 、数据高速读取：采用 Spark SQL读取Hive分区表 ，单活动1亿SKU数据读取耗时缩到至30分钟。
    //         1、超级18活动9亿数据在1小时内读取完成并发带mq队列（任务执行需要获取系统资源，这个过程等待时间不确定看任务等级）
    //         2、数据分为 t+1时效全量已报数据、当天报名实时流水表（结合起来全量数据）
    // 2 、多级缓存预热 ：前置构建Redis缓存（主从各256分片 ，单节点20G内存）存储已报名SKU基础数据 ，以及活动配置数据 ，热点 数据本地缓存预加载 ，解决redis热key问题。
    //         1、全量已报sku都同步构建一份缓存，key=活动id+sku，value=是否报名+sku基础信息，每次消费消息，首先就要判断sku是否已报名
    //         2、活动信息、活动场次信息、标签信息、券信息 同步构建redis+jvm缓存（查询时构建，不存在阻塞只有一个线程去构建）
    //                    redis缓存都只存在单节点，容易造成单台cpu100问题
    //                    本地缓存更新方式：1、定时任务去查询redis再更新jvm缓存 2、redis有变更发送广播mq去执行删除本地缓存，由查询去更新jvm缓存
    // 3 、流量分级治理：拆分校验和结果双MQ通道（支持自动重试与优先级插队）：校验队列：处理复杂校验 ， 占比90%流量 ，结果   队列：处理校验结果上下标 、上下券 ，
    // 拆分快慢mq队列（快队列限流高队列内的sku需要优先处理） 、以及拆分多级mq过滤队列来提升性能。
    //         1、校验队列内部都是查询（实时价，黑白名单查询，资质校验，标签状态，券状态）
    //         2、结果队列执行上下标加减券，发送处理结果mq（校验队列都是查询性能高，结果队列需要调用上游接口，接口执行成功异步去更新mysql，es状态）
    //         3、异常自动重试，重试队列数据过多直接触发告警
    //         4、申请一个独立通道应对紧急场景
    //         5、慢队列只处理 初始化全量的数据的校验、以及当天报名的数据，快队列处理价格中台通过mq推过来的变价sku（实时要求高，队列最好不要有积压）
    //         6、价格中台会把变价的sku通过mq推过来，接受到mq需要查出来正在进行的活动code，通过活动code+sku过滤出不在活动的不是已报名的sku
    //
    // 项目业绩：
    // 1 、性能突破 ：单活动1亿SKU全链路处理时效缩到1小时 ，峰值吞吐达 10W/秒 ，核心校验接口平均响应时间缩短至 100ms以内。
    //      1、mq批量消费，开启并行消费，水平扩容部署jvm实例
    //      2、es查询sku信息，报名状态，全部异构数据到redis，改成从redis查询
    //      3、接触依赖上游接口性能
    //      4、黑白名名单判断，改成线程池并行去执行
    //      5、查询校验逻辑 和 实际执行上下标加减券 拆分开来，结果处理发送到一个独立队列
    //      6、活动信息等热key，使用jvm缓存扛量
    // 2 、资源优化：通过Redis集群弹性扩缩容 ，非大促期间缩容减少占用集群资源 ，优化Redis数据存储 ，集群内存占用下降40%。
    //      1、key的val的字段名简化，缩容占用容量
    // 3 、业务价值：价格策略生效延迟缩短至 30分钟 ，减少大量商家客诉。
    //      1、价格调整后标和券恢复慢，容易引起商家的采销的大量投诉，以及有些重要sku没上标上券容易把事情搞大最后可能会被定义成重大事故
    // 4 、系统稳定性 ：异地多机房部署高可用性 ，异常数据支持自动重试 ，减少人工介入 ，构建全链路监控大盘 ，异常触发实时告警， 定位时效缩到至分钟。
    //      1、所有应用都是多机房部署，mq异常必须支持重试防止数据丢失
    // 5 、可扩展性 ：沉淀 通用价格校验校验组件 ，降低二次开发成本 ，支持各业务线快速接入。
    //      1、能力复用，其他业务线可快速接入
    //      2、场次资质配置模块、数据读取模块，校验模块，结果处理需要各种业务线各种接入去消费
    //      3、topic需要按业务线去拆分，耦合在一起无法按照各自性能要求去调节限流
    //
    // 技术亮点总结 ：Spark高速抽数、JMQ快慢队列分流、JMQ多级队列提升性能 、多级缓存穿透防护 、价格策略变化准实时生效。



    //-----------------------------------------------------------------------------------------------
    // 我京频道生态项目     设计开发                                                                                                                         2021.01-至今
    //项目内容：
    //1 、频道信息存储：基于MySQL设计频道信息存储方案 ，为运营人员提供可视化后台管理界面 ，实现增删改查功能 ，支持频道信息 快速配置与实时生效。
    //      1、频道数据怎么给到其他业务用的，mysql表同步到离线表（准实时）给他其他系统使用
    //2 、多级缓存体系实现 ：Redis分片冗余设计：针对单节点性能瓶颈 ，将单个频道数据冗余存储至32个Redis分片(key:
    //channel_info:频道id:0-31数字) ，客户端随机生成key0-31分散请求压力 ，降低单节点CPU峰值负载 ，本地缓存：构建JVM级本地 缓存 ，结合定时异步刷新。
    //      1、mysql写成功同步写redis，32个分片，通过pipline批量写，极端情况下写失败的分片通过定时任务去补偿修复（每30分钟去校验一次）
    //                                                        写redis成功后，发送mq，消费逻辑内取校验缓存是否存在每个分片中
    //      2、本地缓存构建（查询时构建、定时任务构建）
    //      3、本地缓存更新，mq广播消息去删除本地查询是构建
    //      4、32个key怎么保证平均的分配各个节点（channel_info_频道id_0001 002 标记不同的数字后缀实现分片均衡）
    //      5、对查询命中情况加监控
    //3 、实时数据同步方案 ：MySQL数据变更 ，实时同步更新32个Redis分片数据 ，确保缓存强一致性 ，基于JMQ广播消息机制 ，实时 通知所有服务节点更新本地缓存 ，确保本地缓存最新数据。
    //4 、RPC接口开发：使用Jsf提供单个和批量请求 ，部署集群可支撑5万+QPS ，横向扩展后整体接口QPS可支撑10万/秒。
    //
    //项目业绩：
    //1 、多级缓存架构（ Redis分片+本地缓存）核心查询接口平均响应时间降至5ms ，TP99稳定在15ms以内。
    //2 、Redis分片冗余策略降低单节点CPU使用率稳定在30%以下。
    //3 、MQ广播机制实现秒级内完成全JVM实例本地缓存同步 ，保证缓存数据一致性达。
    //4 、RPC服务稳定支撑峰值5万+QPS ， 日均处理请求量超40亿次 ，无重大故障。




    //-----------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------





}
