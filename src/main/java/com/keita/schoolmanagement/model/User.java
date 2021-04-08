package com.keita.schoolmanagement.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @Column(name = "userID", unique = true, nullable = false)
    private Long userID;

    @NotBlank(message = "Enter a valid first name")
    private String firstName;
    @NotBlank(message = "Enter a valid last name")
    private String lastName;
    @NotBlank(message = "Chose a valid gender")
    private String gender;
    @NotNull(message = "Enter a valid date of birth (mm/dd/yyyy)")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dateOfBirth;
    private int age;
    @NotBlank(message = "Enter a valid phone number")
    private String phoneNum;
    @NotBlank(message = "Enter a valid email")
    @Column(unique = true)
    private String email;

    @OneToOne(mappedBy = "user")
    private Authenticate auth;

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", auth=" + auth +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                '}';
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Authenticate getAuth() {
        return auth;
    }

    public void setAuth(Authenticate auth) {
        this.auth = auth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
