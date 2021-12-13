package cn.chonggong.dynamicProxy;

import cn.chonggong.service.UserService;
import cn.chonggong.service.impl.UserServiceImpl;

/**
 * @author wenguanghua
 * @since 2021-05-18 10:22
 */
public class DynamicProxyTest {
    public static void main(String[] args){
        UserService target = new UserServiceImpl();
        UserService proxy = (UserService) new ProxyFactory(target).getProxyInstance();
        proxy.login();
    };
}
