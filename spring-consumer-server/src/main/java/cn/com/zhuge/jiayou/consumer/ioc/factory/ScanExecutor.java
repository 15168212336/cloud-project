package cn.com.zhuge.jiayou.consumer.ioc.factory;

import java.util.Set;
import java.util.function.Predicate;

/**
 * 委派模式
 */
public class ScanExecutor implements Scan {
    private volatile static ScanExecutor instance;

    @Override
    public Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate) {
        MyFileScanner myFileScanner = new MyFileScanner();
        Set<Class<?>> fileSearch = myFileScanner.search(packageName, predicate);
        MyJarScanner myJarScanner = new MyJarScanner();
        Set<Class<?>> jarSearch = myJarScanner.search(packageName, predicate);
        fileSearch.addAll(jarSearch);
        return fileSearch;
    }

    private ScanExecutor(){}

    public static ScanExecutor getInstance(){
        if(instance == null){
            synchronized (ScanExecutor.class){
                if(instance == null){
                    instance = new ScanExecutor();
                }
            }
        }
        return instance;
    }

}
