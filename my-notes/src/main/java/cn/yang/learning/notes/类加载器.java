package cn.yang.learning.notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shenhui.yang
 * @ClassName: ClassLoaderTest
 * @Description: 寻找类加载器
 * @date 2017/10/10 10:05
 */
public class 类加载器 {
    private static final Logger LOGGER = LoggerFactory.getLogger(类加载器.class);

    public static void main(String[] args) {
        // 从上面的结果可以看出，并没有获取到ExtClassLoader的父Loader，原因是Bootstrap Loader（引导类加载器）是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null。
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        LOGGER.debug(loader + "");
        LOGGER.debug(loader.getParent() + "");
        LOGGER.debug(loader.getParent().getParent() + "");
    }

}
