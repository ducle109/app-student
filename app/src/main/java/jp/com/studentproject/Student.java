package jp.com.studentproject;

public class  Student {
    private int id;
    private String name;
    private int age;
    private String sex;
    private int phoneNumber;

    public Student() {
    }

    public Student(String name, int age, String sex, int phoneNumber) {
        this.name = name;
        this.age = age;
        this.sex = sex;

        this.phoneNumber = phoneNumber;
    }

    public Student(int id, String name, int age, String sex, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}