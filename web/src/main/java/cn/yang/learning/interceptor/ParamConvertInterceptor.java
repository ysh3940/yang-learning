package cn.yang.learning.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class ParamConvertInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.error("ParamConvertInterceptor preHandle");

        if (request instanceof ParamHttpServletRequestWrapper) {
            JSONObject reqBody = getBody(request);
            reqBody.put("requestId", UUID.randomUUID().toString());

            ParamHttpServletRequestWrapper requestWrapper = (ParamHttpServletRequestWrapper) request;
            requestWrapper.setBody(reqBody.toJSONString());

            String okOld = request.getParameter("ok");
            String okNew = okOld + "-" + UUID.randomUUID().toString();

            Map<String, String[]> map = new HashMap<>(requestWrapper.getParameterMap());
            map.put("ok", new String[]{okNew});
            requestWrapper.setParamMap(map);
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.error("ParamConvertInterceptor postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.error("ParamConvertInterceptor afterCompletion");

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
