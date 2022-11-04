package cn.yang.learning.notes.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shenhui.yang
 * @ClassName: ProxyHandler
 * @Description: java动态代理
 * @date 2017/11/14 16:23
 */
public class ProxyHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyHandler.class);

    private Object targetObject;

    /**
     * 绑定委托对象并返回一个代理类
     *
     * @param targetObject
     * @return
     */
    public Object createProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在转调具体目标对象之前，可以执行一些功能处理
        LOGGER.info("在转调具体目标对象之前，可以执行一些功能处理");

        // 转调具体目标对象的方法
        Object object = method.invoke(targetObject, args);

        // 在转调具体目标对象之后，可以执行一些功能处理
        LOGGER.info("在转调具体目标对象之后，可以执行一些功能处理");

        return object;
    }

}
