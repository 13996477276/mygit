import dto.Driver;
import po.Person;

/**
 * @author wenguanghua
 * @since 2021-04-19 22:11
 */
public class Test {
    public static void main(String[] args){
        System.out.println("helloworld");
        Person person1 = new Person(21,"zhangsan1","china");
        Person person2 = new Person(22,"zhangsan2","aeri");
        Person person3 = new Person(23,"zhangsan3","jap");
//        Person.country = "124";
        System.out.println("person1:"+person1);
        System.out.println("person1:"+person1.toString());
        System.out.println("person2:"+person2.toString());
        System.out.println("person3:"+person3.toString());
        Driver driver = new Driver();
        driver.setId("123");
        driver.setLicense("c1");
        driver.setName("123");
        System.out.println("driver:"+driver.toString());
        System.out.println("driver:"+driver.getName());

    }
}
