package cn.yang.learning.web;

import cn.yang.learning.core.web.BaseRequest;
import cn.yang.learning.core.web.BaseResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HealthController {

    @RequestMapping("/ok")
    @ResponseBody
    public BaseResponse ok(@RequestBody BaseRequest request, String ok) {
        System.out.println(request.getRequestId());
        return BaseResponse.success("ok = " + request.getRequestId() + " ok = " + ok);
    }

}
