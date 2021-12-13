package po;

/**
 * @author wenguanghua
 * @since 2021-04-23 09:32
 */
public class Student extends Person {
    private String studentNo;
    private String classNo;

    public Student(int age, String name) {
        super(age, name);
    }

    public Student(int age, String name, String studentNo, String classNo) {
        super(age, name);
        this.studentNo = studentNo;
        this.classNo = classNo;
    }

    public Student(int age, String name, String country, String studentNo, String classNo) {
        super(age, name, country);
        this.studentNo = studentNo;
        this.classNo = classNo;
    }

    public Student(int age, String studentNo, String classNo) {
        super(age);
        this.studentNo = studentNo;
        this.classNo = classNo;
    }

    public Student(int age) {
        super(age);
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

}
