package dto;

import po.Person;

/**
 * @author wenguanghua
 * @since 2021-04-23 09:40
 */
public class Driver extends Person {
    private String id;
    private String license;

    /*无参构造方法*/
    public Driver(){
    }
    public Driver(int age, String name, String id, String license) {
        super(age, name);
        this.id = id;
        this.license = license;
    }

    public Driver(int age, String name, String country, String id, String license) {
        super(age, name, country);
        this.id = id;
        this.license = license;
    }

    public Driver(int age, String id, String license) {
        super(age);
        this.id = id;
        this.license = license;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", license='" + license + '\'' +
                ", age=" + age +
                '}';
    }
}
