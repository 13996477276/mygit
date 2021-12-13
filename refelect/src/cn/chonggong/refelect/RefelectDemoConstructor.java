package cn.chonggong.refelect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author wenguanghua
 * @since 2021-05-11 15:37
 * 测试获取Constructor
 */
public class RefelectDemoConstructor {
    public static void main(String[] args) throws Exception {
        /*第三种：通过Class.forName方式*/
        Class soldierClass = Class.forName("cn.chonggong.po.Soldier");
        /*注意：Constructor总是当前类定义的构造方法，和父类无关，因此不存在多态的问题。*/
//        Constructor constructor = soldierClass.getConstructor();
//        System.out.println("constructor:"+constructor);
        /*getDeclaredConstructor(Class...)：获取某个Constructor；*/
//        System.out.println("==============================");
//        Constructor declaredConstructor = soldierClass.getDeclaredConstructor();
//        System.out.println("declaredConstructor:"+declaredConstructor);
//        System.out.println("============================");
//        Constructor[] constructors = soldierClass.getConstructors();
//        /*getConstructors()：获取所有public的Constructor；*/
//        for(Constructor cc:constructors){
//            System.out.println("Constructor1:"+cc);
//        };
//        System.out.println("============================");
//        Constructor[] declaredConstructors = soldierClass.getDeclaredConstructors();
//        /*getConstructors()：获取所有public的Constructor；*/
//        for(Constructor cc:declaredConstructors){
//            System.out.println("declaredConstructors:"+cc);
//        };
//        /*调用public的Constructor时，创建实例对象。*/
//        Constructor declaredConstructor1 = soldierClass.getDeclaredConstructor(String.class);
//        Object obj1 = declaredConstructor1.newInstance("坦克兵");
//        System.out.println("declaredConstructor1:"+obj1);
//
//        /*调用非public的Constructor时，必须首先通过setAccessible(true)设置允许访问。*/
        Constructor declaredConstructor2 = soldierClass.getDeclaredConstructor(int.class);
        declaredConstructor2.setAccessible(true);
        Object obj2 = declaredConstructor2.newInstance(123);
        System.out.println("declaredConstructor2:"+obj2);
    };
}
