package ac.app.contract.student.logic.entity;

/**
 * Author: chenquan
 * Date: 2018/11/9
 * Package: ac.app.contract.student.logic.entity
 * Description:
 */
public class Student {
    private String id;
    private String name;
    private int age;
    private String male;
    private String fatherName;
    private String motherName;
    private String grade;
    private String hobby;
    private String course;
    private String girlFriend;

    public Student() {
    }

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String var1) {
        this.id = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getMale() {
        return this.male;
    }

    public void setMale(String var1) {
        this.male = var1;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String var1) {
        this.fatherName = var1;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String var1) {
        this.motherName = var1;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String var1) {
        this.grade = var1;
    }

    public String getHobby() {
        return this.hobby;
    }

    public void setHobby(String var1) {
        this.hobby = var1;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String var1) {
        this.course = var1;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGirlFriend() {
        return girlFriend;
    }

    public void setGirlFriend(String girlFriend) {
        this.girlFriend = girlFriend;
    }

    public String toString() {
        return "Student{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", age=" + this.age + ", male='"
                + this.male + '\'' + ", fatherName='" + this.fatherName + '\'' + ", motherName='" + this.motherName
                + '\'' + ", grade='" + this.grade + '\'' + ", hobby='" + this.hobby + '\'' + ", course='" + this.course
                + '\'' + ", girlFreind='" + this.girlFriend + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        return this.id.equals(student.getId());
    }
}

