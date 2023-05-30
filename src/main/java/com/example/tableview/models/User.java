package com.example.tableview.models;

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String department;
    private String gender;

    public User() {
    }

    public User(Long id, String username, String email, String password, String department, String gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.department = department;
        this.gender = gender;
    }

    public User(String username, String email, String password, String department, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.department = department;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
