package cn.yang.learning.web;

import cn.yang.learning.core.web.BaseResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HystrixController {

    @GetMapping("/hystrix")
    @ResponseBody
    public BaseResponse hystrix() {
        return BaseResponse.success("hystrix");
    }

}
