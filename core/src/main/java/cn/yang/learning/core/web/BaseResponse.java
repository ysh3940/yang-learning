package cn.yang.learning.core.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    Boolean success;
    String code;
    Object data;
    String msg;

    public static BaseResponse success(Object data) {
        return BaseResponse.builder().success(true).code(Integer.toString(HttpStatus.OK.value())).data(data).build();
    }

    public static BaseResponse fail(String msg) {
        return BaseResponse.builder().success(false).code(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value())).msg(msg).build();
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

}
