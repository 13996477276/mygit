package cn.chonggong.refelect;

import cn.chonggong.po.Soldier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wenguanghua
 * @since 2021-05-11 15:37
 * 测试获取Method
 */
public class RefelectDemoMethod {
    public static void main(String[] args) throws Exception {

         /*第三种：通过Class.forName方式*/
        Class soldierClass = Class.forName("cn.chonggong.po.Soldier");
         /*Method getMethod(name, Class...)：获取某个public的Method（包括父类）*/
//        Method competition = soldierClass.getDeclaredMethod("shot");
//        Method eat = soldierClass.getMethod("eat");
//        System.out.println(competition);
//        System.out.println(eat);
//        /*Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）*/
//        Method competition2 = soldierClass.getDeclaredMethod("competition");
//        // Method eat2 = soldierClass.getDeclaredMethod("eat");
//        System.out.println(competition2);
//        // System.out.println(eat2);
//        /*Method[] getMethods()：获取所有public的Method（包括父类）*/
//        Method[] methods = soldierClass.getMethods();
//        for(Method method:methods){
//            System.out.println("methods:"+method);
//        };
//        System.out.println("============================");
//        /*Method[] getDeclaredMethods()：获取当前类的所有Method（不包括父类）*/
//        Method[] declaredMethods = soldierClass.getDeclaredMethods();
//        for(Method method:declaredMethods){
//            System.out.println("declaredMethods:"+method);
//        };
//        /*测试使用方法invoke（）*/
//        //调用public修饰的方法
//        Soldier soldier1 = new Soldier();
//        Method competition3 = soldierClass.getDeclaredMethod("competition");
//        competition3.invoke(soldier1);
//        //调用private修饰的方法
//        Method setSpecialty = soldierClass.getDeclaredMethod("setSpecialty", String.class);
//        setSpecialty.setAccessible(true);
//        setSpecialty.invoke(soldier1,"狙击步枪");
//        System.out.println("soldier1.getSpecialty(): "+soldier1.getSpecialty());

    };
}
