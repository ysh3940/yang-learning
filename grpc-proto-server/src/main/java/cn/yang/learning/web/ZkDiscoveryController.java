package cn.yang.learning.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zk")
public class ZkDiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    @ResponseBody
    public Object serviceUrl() {
        List<String> list = discoveryClient.getServices();
        Map<String, List<ServiceInstance>> serviceInstanceMap = new HashMap<>();
        for (String s : list) {
            serviceInstanceMap.put(s, discoveryClient.getInstances(s));
        }
        return serviceInstanceMap;
    }

}
