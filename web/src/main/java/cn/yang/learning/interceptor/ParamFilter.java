package cn.yang.learning.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@WebFilter(filterName = "paramFilter", urlPatterns = "/*")
public class ParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ParamHttpServletRequestWrapper paramHttpServletRequestWrapper = null;
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            paramHttpServletRequestWrapper = new ParamHttpServletRequestWrapper(req);
        } catch (Exception e) {
            log.warn("ParamHttpServletRequestWrapper error = {}", e.getMessage(), e);
        }

        ParamHttpServletResponseWrapper responseWrapper = new ParamHttpServletResponseWrapper((HttpServletResponse) response);

        chain.doFilter((Objects.isNull(paramHttpServletRequestWrapper) ? request : paramHttpServletRequestWrapper), responseWrapper);


        String respBody = responseWrapper.getBodyString();
        JSONObject respJson = JSON.parseObject(respBody);
        respJson.put("responseId", UUID.randomUUID().toString());
        System.err.println(respJson.toJSONString());

//        response.setContentLength(respBody.length());
        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().write(respJson.toJSONString().getBytes());

    }

}
