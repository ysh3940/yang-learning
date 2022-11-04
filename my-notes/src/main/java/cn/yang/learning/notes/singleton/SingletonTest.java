package cn.yang.learning.notes.singleton;

public class SingletonTest {
}

class t1 {
    private static t1 t1;

    private t1() {
    }

    public synchronized static t1 getInstance() {
        if (t1 != null) {
            return t1;
        }

        t1 = new t1();
        return t1;
    }

}

class t2 {
    private static volatile t2 t2;

    private t2() {
    }

    public static t2 getInstance() {
        if (t2 != null) {
            return t2;
        }

        synchronized (t2) {
            if (t2 != null) {
                return t2;
            }

            t2 = new t2();
            return t2;
        }
    }

}

class t3 {

    public static t3 getInstance() {
        return t31.t3;
    }

    public static class t31 {
        private static t3 t3 = new t3();
    }


}

// 用枚举实现

