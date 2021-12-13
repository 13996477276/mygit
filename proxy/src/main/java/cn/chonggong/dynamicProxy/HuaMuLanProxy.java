package cn.chonggong.dynamicProxy;

import cn.chonggong.service.Girl;
import cn.chonggong.service.impl.HuaMuLan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wenguanghua
 * @since 2021-05-21 10:36
 */
public class HuaMuLanProxy implements InvocationHandler {
    private HuaMuLan girl;
    public HuaMuLanProxy(HuaMuLan girl){
        this.girl = girl;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//增强器，
        System.out.println("对象参数："+girl);
        System.out.println("调用的方法是："+method.getName());
        System.out.println("调用的方法的参数是："+args[0]);
        dosomethingBefore();
        Object ret = method.invoke(girl,args);
        dosomethingAfter();
        return ret;
    }
    private void dosomethingBefore(){
        System.out.println("花木兰家人说，我得先调查一下这个小伙子的背景。。。");
    }
    private void dosomethingAfter(){
        System.out.println("花木兰家人问，出去约会感觉怎么样啊。。。");
    }
    public Object getProxyInstance(){//调度者，约会花木兰的时候，需要有家人通知，有时候是爸爸，有时候是妈妈
        return Proxy.newProxyInstance(girl.getClass().getClassLoader(),girl.getClass().getInterfaces(),this);
    }
}
