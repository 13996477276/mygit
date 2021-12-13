package cn.chonggong.refelect;

import cn.chonggong.po.Person;
import cn.chonggong.po.Soldier;

import java.lang.reflect.Field;

/**
 * @author wenguanghua
 * @since 2021-05-11 15:37
 * 测试获取field
 */
public class RefelectDemoField {
    public static void main(String[] args) throws Exception {
        Soldier ss = new Soldier();
        /*第一种：通过Object.class方法*/
        Class c1 = Soldier.class;
        /*第二种：通过对象的getClass方法获取class对象*/
        Class c2 = ss.getClass();
        /*第三种：通过Class.forName方式*/
        Class c3 = Class.forName("cn.chonggong.po.Soldier");
        System.out.println(c1==c2);
        System.out.println(c3==c2);
//        System.out.println("============================");
//        /*Field getField(name)：根据字段名获取某个public的field（包括父类）*/
        Soldier soldier = new Soldier();
        Field field1 = c1.getField("no");
        System.out.println("field1:"+field1);
        field1.set(soldier,111);
        Object value1 = field1.get(soldier);
        System.out.println("value1:"+value1);
//        System.out.println("============================");
//        /*Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）*/
//        Field field2 = c1.getDeclaredField("specialty");//注意，只有getDeclaredField才能获取到私有对象字段
//        System.out.println("field2:"+field2);
//        /*对于私有的field，需要设置setAccessible(true)*/
//        field2.setAccessible(true);
//        field2.set(soldier,"格斗");
//        Object value2 = field2.get(soldier);
//        System.out.println("value2:"+value2);
//        /*获取所有public的field（包括父类）*/
//        Field[] fields = c1.getFields();
//        for(Field field:fields){
//            System.out.println("fields:"+field);
//        };
//        System.out.println("============================");
//        /*获取当前类的所有field（不包括父类）*/
//        Field[] declaredFields = c1.getDeclaredFields();
//        for(Field field:declaredFields){
//            System.out.println("declaredFields:"+field);
//        };
    };
}
