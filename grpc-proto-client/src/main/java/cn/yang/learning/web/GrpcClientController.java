package cn.yang.learning.web;

import cn.yang.learning.service.GrpcClientMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcClientController {

    @Autowired
    private GrpcClientMyService myService;

    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "Michael") final String name) {
        return this.myService.sendMessage(name);
    }

}