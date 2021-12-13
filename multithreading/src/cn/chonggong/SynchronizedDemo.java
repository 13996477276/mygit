package cn.chonggong;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenguanghua
 * @since 2021-06-11 10:21
 */
public class SynchronizedDemo{
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        for(int i=0;i<10000;i++){
            Thread thread = new Thread(){//匿名内部类
                @Override
                public void run(){
                    synchronized (list){
                        list.add(Thread.currentThread().getName());//将线程名字添加到集合中
                    }
//                    list.add(Thread.currentThread().getName());//将线程名字添加到集合中
                }
            };
            thread.start();
        };
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("输出list的size："+list.size());
    };
}
