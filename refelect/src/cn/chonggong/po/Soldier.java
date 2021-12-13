package cn.chonggong.po;

/**
 * @author wenguanghua
 * @since 2021-05-10 14:17
 */
public class Soldier extends Person {
    public int no;
    private String specialty;
    public Soldier(){}
    public void competition(){
        System.out.println("军队大比武....");
    }
    private void shot(){
        System.out.println("练习射击....");
    }
    private Soldier(int no){
        this.no = no;
    }
    public Soldier(String specialty){
        this.specialty = specialty;
    }
    public Soldier(int no, String specialty) {
        this.no = no;
        this.specialty = specialty;
    }
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSpecialty() {
        return specialty;
    }

    private void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Soldier{" +
                "no=" + no +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
