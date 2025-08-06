package ra.exercise.model.entity;

import java.time.LocalDate;

public class User {
    private String name;
    private int age;
    private LocalDate birthday;
    private String email;
    private String phone;

    public User(String name, int age, LocalDate birthday, String email, String phone) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
