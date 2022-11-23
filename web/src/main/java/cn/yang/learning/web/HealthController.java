package cn.yang.learning.web;

import cn.yang.learning.core.web.BaseRequest;
import cn.yang.learning.core.web.BaseResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HealthController {

    @RequestMapping("/ok")
    @ResponseBody
    public BaseResponse ok(@RequestBody BaseRequest request) {
        return BaseResponse.success(request.getRequestId());
    }


}
