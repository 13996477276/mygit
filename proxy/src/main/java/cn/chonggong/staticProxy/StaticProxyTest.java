package cn.chonggong.staticProxy;

import cn.chonggong.service.Girl;
import cn.chonggong.service.UserService;
import cn.chonggong.service.impl.HuaMuLan;
import cn.chonggong.service.impl.UserServiceImpl;

/**
 * @author wenguanghua
 * @since 2021-05-17 17:01
 */
public class StaticProxyTest {
    public static void main(String[] args){
//        UserService userService = new UserServiceImpl();
//        UserStaticProxy userStaticProxy = new UserStaticProxy(userService);
//        userStaticProxy.login();
        //静态方式查看花木兰
        Girl girl = new HuaMuLan();
        UserStaticProxy userStaticProxy1 = new UserStaticProxy(girl);
        userStaticProxy1.date("2021年5月26日");
    };
}
