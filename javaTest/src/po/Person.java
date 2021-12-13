package po;

/**
 * @author wenguanghua
 * @since 2021-04-20 22:31
 */
public class Person {
    protected int age;
    private String name;
    public static String country;
    public Person(){

    }
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public Person(int age, String name,String country) {
        this.age = age;
        this.name = name;
        this.country = country;
    }
    public Person(int age) {

        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        Person.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +", country='" + country + '\''+
                '}';
    }
}
