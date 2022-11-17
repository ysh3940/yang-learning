package cn.yang.learning.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

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

        chain.doFilter((Objects.isNull(paramHttpServletRequestWrapper) ? request : paramHttpServletRequestWrapper), response);
    }

}
