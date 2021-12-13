package cn.chonggong;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wenguanghua
 * @since 2021-06-11 10:21
 */
public class SynchronizedDemo2 {
    public static void main(String[] args){
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for(int i=0;i<10000;i++){
            Thread thread = new Thread(){//匿名内部类
                @Override
                public void run(){
                    list.add(Thread.currentThread().getName());//将线程名字添加到集合中
                }
            };
            thread.start();
        };
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("输出list的size："+list.size());
    };
}
