package po;

/**
 * @author wenguanghua
 * @since 2021-04-23 11:05
 */
public class HuaFu {
        private int age = 60;
        public String name = "花弧";
        public String sex = "man";
        public void killEnemy(){
        System.out.println("花弧在杀敌");
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "HuaFu{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
