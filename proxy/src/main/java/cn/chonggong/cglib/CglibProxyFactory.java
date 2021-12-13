package cn.chonggong.cglib;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wenguanghua
 * @since 2021-05-18 10:41
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;//维护一个目标对象
    public CglibProxyFactory(Object target) {
        this.target = target;
    }
    //为目标对象生成代理对象
    public Object getProxyInstance() {
        //工具类,增强器
        Enhancer en = new Enhancer();
        //设置父类
        en.setSuperclass(target.getClass());
        //设置回调函数
        en.setCallback(this);
        //创建子类对象代理
        return en.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("obj:"+target);
        System.out.println("method:"+method.getName());
        System.out.println("args:"+args[0]);
        System.out.println("开启事务");
        // 执行目标对象的方法
        Object returnValue = method.invoke(target, args);
        System.out.println("关闭事务");
        return returnValue;
    }
}
