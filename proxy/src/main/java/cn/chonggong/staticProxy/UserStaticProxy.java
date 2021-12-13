package cn.chonggong.staticProxy;

import cn.chonggong.service.Girl;
import cn.chonggong.service.UserService;

/**
 * @author wenguanghua
 * @since 2021-05-17 16:56
 */
public class UserStaticProxy implements UserService,Girl {
    private UserService target;
    private Girl girl;
    public UserStaticProxy(UserService target){
        this.target = target;
    }
    public UserStaticProxy(Girl girl){
        this.girl = girl;
    }
    public void login() {
        System.out.println("登录前处理逻辑");
        target.login();
        System.out.println("登录后处理数据");
    }
    public void date(String name) {
        System.out.println("约会前背景调查");
        girl.date("123");
        System.out.println("约会后效果反馈");
    }
    public void watchMovie() {
        System.out.println("看电影前背景调查");
        girl.watchMovie();
        System.out.println("看电影后进展反馈");
    }
}
