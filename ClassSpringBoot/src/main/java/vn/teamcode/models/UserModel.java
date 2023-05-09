package vn.teamcode.models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="tblUser")
public class UserModel {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1 // increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(nullable = false, unique = true, length = 300)
    private String userName;
    private String fullName;
    private String address;
    private int year;

    @Transient
    private int age;
    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - year;
    }

    public UserModel() {}
    public UserModel(String userName, String fullName, String address, int year) {
        this.userName = userName;
        this.fullName = fullName;
        this.address = address;
        this.year = year;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", year=" + year +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
