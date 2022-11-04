package cn.yang.learning.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {
    String code;
    Object data;

    public static BaseResponse success(String code, Object data) {
        return BaseResponse.builder().code(code).data(data).build();
    }

}
