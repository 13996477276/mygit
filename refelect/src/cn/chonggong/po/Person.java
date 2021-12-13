package cn.chonggong.po;

/**
 * @author wenguanghua
 * @since 2021-05-10 14:09
 */
public class Person {
    public String name;
    public String age;
    public void eat(){
        System.out.println("吃饭中.....");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
