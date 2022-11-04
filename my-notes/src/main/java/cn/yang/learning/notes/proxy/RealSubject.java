package cn.yang.learning.notes.proxy;

/**
 * @author shenhui.yang
 * @ClassName: RealSubject
 * @Description: java动态代理
 * @date 2017/11/14 16:22
 */
public class RealSubject implements Subject {

    public void doSomething() {
        System.out.println("call doSomething()");
    }

}
