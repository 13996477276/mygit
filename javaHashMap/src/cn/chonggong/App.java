package cn.chonggong;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenguanghua
 * @since 2021-06-03 13:25
 */
public class App {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<String, String>();
//         App map=new App();
//         map.put("张三", "张三");//存储位置:4
//         map.put("李四", "李四");//存储位置:6
//         map.put("王五", "王五");//存储位置:0
//         map.put("孙悟空", "孙悟空");//存储位置:4
//         map.put("Monkey", "Monkey");//存储位置:4
//         System.out.println(map.get("孙悟空"));
        Map<String, String> map1 = new HashMap<String, String>();
        for(int i=0;i<10000;i++){
            map1.put("孙悟空", "孙悟空");
        }
        System.out.println("map1.size():"+map1.size());
    }
        public void put(String key,String value){
            System.out.printf("key:%s:::::::hash值:%s::::::存储位置:%s\r\n",
                    key, key.hashCode(), Math.abs(key.hashCode() % 15));
        }
    }
