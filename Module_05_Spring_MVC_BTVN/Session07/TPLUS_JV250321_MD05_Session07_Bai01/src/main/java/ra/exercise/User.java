package ra.exercise;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {
    @NotBlank(message = "Ten khong duoc de trong")
    private String name;

    @NotNull(message = "Tuoi khong duoc de trong")
    private Integer age;

    @NotBlank(message = "Dia chi khong duoc de trong")
    private String address;

    public  User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
