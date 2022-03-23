package cn.com.zhuge.jiayou.consumer.ioc.factory;

import cn.com.zhuge.jiayou.consumer.ioc.dao.HelloDao;
import cn.com.zhuge.jiayou.consumer.ioc.dao.HelloDaoImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    private static Properties properties;

    private static Map<String,Object> beanMap = new HashMap();

    static {
        try {
            properties = new Properties();
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("factory.properties"));
            System.out.println("-------------------------" + properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getDao() {
        String var = properties.getProperty("helloDao");

            try {
                Class<?> aClass = Class.forName(var);
                String className = aClass.getSimpleName();
                System.out.println("simpleName:"+className);
                if (!beanMap.containsKey(className)) {
                    synchronized (BeanFactory.class) {
                        if (!beanMap.containsKey(className)) {
                            beanMap.put(className, aClass.newInstance());
                        }
                    }
                }
                return beanMap.get(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        return null;
    }
}
