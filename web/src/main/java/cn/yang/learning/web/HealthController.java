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

    @RequestMapping("/ok2")
    @ResponseBody
    public BaseResponse ok2(@RequestBody BaseRequest request, String ok) {
        System.out.println("转发 = " + request.getRequestId());
        return BaseResponse.success("转发ok = " + request.getRequestId() + " 转发ok = " + ok);
    }


}
