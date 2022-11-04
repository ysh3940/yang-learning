package cn.yang.learning.web;

import cn.yang.learning.web.response.BaseResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HealthController {

    @GetMapping("/ok")
    @ResponseBody
    public BaseResponse ok() {
        return BaseResponse.success("ok");
    }

}
