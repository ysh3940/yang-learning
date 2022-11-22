package cn.yang.learning.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@WebFilter(filterName = "paramFilter", urlPatterns = "/param/*")
public class ParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        ParamHttpServletRequestWrapper requestWrapper = new ParamHttpServletRequestWrapper(req);
        ParamHttpServletResponseWrapper responseWrapper = new ParamHttpServletResponseWrapper(resp);

        String uri = req.getRequestURI();

        JSONObject reqBody = getBody(requestWrapper);
        reqBody.put("requestId", UUID.randomUUID().toString());
        requestWrapper.setBody(reqBody.toJSONString());

        String okOld = request.getParameter("ok");
        String okNew = okOld + "-" + UUID.randomUUID().toString();

        Map<String, String[]> map = new HashMap<>(requestWrapper.getParameterMap());
        map.put("ok", new String[]{okNew});
        requestWrapper.setParamMap(map);


        String api = uri.substring("/param".length());
        request.getRequestDispatcher(api).forward(requestWrapper, responseWrapper);


        String respBody = responseWrapper.getBodyString();
        System.err.println(respBody);

        JSONObject respJson = JSON.parseObject(respBody);
        respJson.put("responseId", UUID.randomUUID().toString());

        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().write(respJson.toJSONString().getBytes());

    }

    public JSONObject getBody(HttpServletRequest request) {
        StringBuffer body = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (body.length() > 0) {
            return JSON.parseObject(body.toString());
        }

        return null;
    }

}
