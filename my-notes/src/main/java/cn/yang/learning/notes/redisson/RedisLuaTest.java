package cn.yang.learning.notes.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RedisLuaTest {

    @Test
    public void test() {
        // 默认连接地址 127.0.0.1:6379
        // RedissonClient redisson = Redisson.create();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        String key = "person1";
        RList<String> rList = redisson.getList(key);
//    rList.add("mary");
//    rList.add("jack");
//    rList.add("peter");

        log.info(rList.size() + "");
//    log.info(rList.getName());
//    List<String> list = rList.range(0, -1);
//    for (String s : list) {
//      log.info(s);
//    }

        String lua = "local key=KEYS[1]\n" +
                "\n" +
                "local list=redis.call(\"lrange\",key,0,-1);\n" +
                "\n" +
                "return list;";

        RScript rScript = redisson.getScript();
        List<Object> keys = new ArrayList<>();
        keys.add(key);
        List<Object> objectList = rScript.eval(RScript.Mode.READ_ONLY, lua, RScript.ReturnType.MAPVALUELIST, keys);
        for (Object s : objectList) {
            log.info(s.toString());
        }

    }

}
