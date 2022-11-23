package cn.yang.learning.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@WebFilter(filterName = "paramConvertFilter", urlPatterns = "/param/*")
public class ParamConvertFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        RequestWrapper requestWrapper = new RequestWrapper(req);
        ResponseWrapper responseWrapper = new ResponseWrapper(resp);

        String uri = req.getRequestURI();

        JSONObject reqBody = JSON.parseObject(requestWrapper.getBody());
        reqBody.put("requestId", UUID.randomUUID().toString());
        requestWrapper.setBody(reqBody.toJSONString());


        String api = uri.substring("/param".length());
        request.getRequestDispatcher(api).forward(requestWrapper, responseWrapper);


        String respBody = responseWrapper.getBodyString();

        JSONObject respJson = JSON.parseObject(respBody);
        respJson.put("responseId", UUID.randomUUID().toString());

        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().write(respJson.toJSONString().getBytes());
    }


}
