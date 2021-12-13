package cn.chonggong.refelect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author wenguanghua
 * @since 2021-05-10 14:36
 */
public class RefelectTest {
    public static void main(String[] args) throws Exception {
        //生成properties对象
        Properties properties = new Properties();
        //寻找配置文件
        ClassLoader classLoader = RefelectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        //加载配置文件内容
        properties.load(is);
        //获取配置文件具体项目内容
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        //将类加载进入内存
        Class cl = Class.forName(className);
        Object obj = cl.newInstance();
        Method method = cl.getMethod(methodName);
        method.invoke(obj);
    };
}
