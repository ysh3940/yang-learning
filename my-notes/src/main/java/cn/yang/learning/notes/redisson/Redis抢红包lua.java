package cn.yang.learning.notes.redisson;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Redis抢红包lua {

    static String hongBaoList = "hongBaoList";
    static String hongBaoConsumedList = "hongBaoConsumedList";
    static String hongBaoConsumedMap = "hongBaoConsumedMap";

    public void generateTestData(RedissonClient redisson) {
        RList<String> rList = redisson.getList(hongBaoList, StringCodec.INSTANCE);
        for (int j = 1; j <= 10; ++j) {
            JSONObject object = new JSONObject();
            object.put("id", j);
            object.put("money", j);
            rList.add(object.toJSONString());
        }
    }

    @Test
    public void test() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

//    generateTestData(redisson);

        String lua = "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n" + "  return nil\n" + "else\n" + "  local hongBao = redis.call('rpop', KEYS[1]);\n" + "  if hongBao then\n"
                + "    local x = cjson.decode(hongBao);\n" + "    x['userId'] = KEYS[4];\n" + "    local re = cjson.encode(x);\n" + "    redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"
                + "    redis.call('lpush', KEYS[2], re);\n" + "    return re;\n" + "  end\n" + "end\n" + "return nil";

        RScript script = redisson.getScript(StringCodec.INSTANCE);
        List<Object> keys = new ArrayList<>();
        keys.add(hongBaoList);
        keys.add(hongBaoConsumedMap);
        keys.add(hongBaoConsumedList);
        keys.add("uid-6");

        Object object = script.eval(RScript.Mode.READ_WRITE, lua, RScript.ReturnType.VALUE, keys);
        if (object != null) {
            log.info(object.toString());
        } else {
            log.info("object is null");
        }
    }

    @Test
    public void test2() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        String key = hongBaoConsumedMap;
        RList<String> rList = redisson.getList(key);


        log.info(rList.size() + "");


        String lua = "local key=KEYS[1]\n" +
                "\n" +
                "local list=redis.call(\"lrange\",key,0,-1);\n" +
                "\n" +
                "return list;";

        RScript rScript = redisson.getScript(StringCodec.INSTANCE);
        List<Object> keys = new ArrayList<>();
        keys.add(key);
        List<Object> objectList = rScript.eval(RScript.Mode.READ_ONLY, lua, RScript.ReturnType.MAPVALUELIST, keys);
        for (Object s : objectList) {
            log.info(s.toString());
        }

    }

}
