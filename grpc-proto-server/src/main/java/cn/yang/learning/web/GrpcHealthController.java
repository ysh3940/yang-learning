package cn.yang.learning.web;

import cn.yang.learning.core.web.BaseResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/grpc")
public class GrpcHealthController {

    @GetMapping("/ok")
    @ResponseBody
    public BaseResponse ok() {
        return BaseResponse.success("grpc-ok");
    }

}
