package cn.yang.learning.notes.code;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * 按顺序打印abc：abcabcabc
 */
public class AbcAbcAbc {

    public static void main(String[] args) {
//    final String str= "abc";
//    ExecutorService executorService= Executors.newFixedThreadPool(3);
//    executorService.execute(new Runnable() {
//      public void run() {
//        System.out.println("1"+str);
//      }
//    });
//    executorService.execute(new Runnable() {
//      public void run() {
//        System.out.println("2"+str);
//      }
//    });
//    executorService.execute(new Runnable() {
//      public void run() {
//        System.out.println("3"+str);
//      }
//    });

//    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        SynchronousQueue synchronousQueue = new SynchronousQueue();
        try {
            synchronousQueue.take();
        } catch (Exception e) {
        }
        System.out.println(synchronousQueue.offer(1));
        System.out.println(synchronousQueue.offer(2));
        System.out.println(synchronousQueue.offer(3));


        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1", "1");

        Set set = new HashSet();
        set.add(1);
        set.add(11);
        System.out.println(set.size());
    }

}
