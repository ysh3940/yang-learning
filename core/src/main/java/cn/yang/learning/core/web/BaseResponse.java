package cn.yang.learning.core.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {
    Boolean success;
    String code;
    Object data;

    public static BaseResponse success(Object data) {
        return BaseResponse.builder().success(true).code("0").data(data).build();
    }

}
