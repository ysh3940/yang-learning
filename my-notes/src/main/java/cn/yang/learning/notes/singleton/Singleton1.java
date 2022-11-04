package cn.yang.learning.notes.singleton;

public class Singleton1 {
    private static volatile Singleton1 singleton1;

    public static Singleton1 getInstance() {
        if (singleton1 != null) {
            return singleton1;
        } else {
            synchronized (singleton1) {
                if (singleton1 == null) {
                    singleton1 = new Singleton1();
                }
                return singleton1;
            }
        }
    }

}

class Singleton2 {
    public static class Singleton {
        public static final Singleton2 singleton = new Singleton2();
    }
}

enum Singleton3 {
    SINGLETON;

    public void test() {
        System.out.println("test");
    }
}
