package cn.yang.learning.notes.proxy;

/**
 * @author shenhui.yang
 * @ClassName: DynamicProxy
 * @Description: java动态代理
 * @date 2017/11/14 16:27
 */
public class DynamicProxy {

    public static void main(String args[]) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyHandler handler = new ProxyHandler();
        Subject proxySubject = (Subject) handler.createProxyInstance(new RealSubject());
        proxySubject.doSomething();
    }

}
