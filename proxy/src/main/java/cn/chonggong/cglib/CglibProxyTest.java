package cn.chonggong.cglib;

import cn.chonggong.service.UserService;
import cn.chonggong.service.impl.UserServiceImpl;
import cn.chonggong.service.impl.XiaoFang;

/**
 * @author wenguanghua
 * @since 2021-05-18 11:04
 */
public class CglibProxyTest {
    public static void main(String[] args){
//        //目标对象
//        UserService target = new UserServiceImpl();
//        System.out.println(target.getClass());
//        //代理对象
//        UserService proxy = (UserService) new CglibProxyFactory(target).getProxyInstance();
//        System.out.println(proxy.getClass());
//        //执行代理对象方法
//        proxy.login();
//        System.out.println("=======================");
        XiaoFang xiaoFang = new XiaoFang();
        XiaoFang xiaoFangProxy = (XiaoFang) new CglibProxyFactory(xiaoFang).getProxyInstance();
        xiaoFangProxy.date("2021年5月26日");

    };
}
