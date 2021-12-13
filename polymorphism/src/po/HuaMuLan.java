package po;

/**
 * @author wenguanghua
 * @since 2021-04-23 11:08
 */
public class HuaMuLan extends HuaFu{
    private int age = 30;
    public String name = "花木兰";
    private String sex = "woman";
    public void killEnemy(){
        System.out.println("花木兰在杀敌");
    }
    public void makeUp(){
        System.out.println("花木兰在化妆");
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }
}
