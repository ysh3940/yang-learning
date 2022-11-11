package cn.yang.learning.micronaut.web;


import cn.yang.learning.core.web.BaseResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import io.reactivex.Single;

import javax.validation.constraints.NotBlank;

@Controller("/")
@Validated
public class HelloController {

    @Get(uri = "/hello/{name}", produces = MediaType.TEXT_PLAIN)
    public Single<String> hello(@NotBlank String name) {
        return Single.just("Hello " + name + "!");
    }

    @Get(uri = "/hello/json", produces = MediaType.APPLICATION_JSON)
    public BaseResponse helloJson() {
        return BaseResponse.success("hello json");
    }

}
