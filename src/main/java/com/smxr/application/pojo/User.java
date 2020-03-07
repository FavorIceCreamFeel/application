package com.smxr.application.pojo;

import java.util.Objects;

/**
 * @author smxr
 * @date 2019/11/24
 * @time 0:20
 * 用户表
 */
public class User {
    private String userName;//昵称、姓名
    private String userPwd;//密码
    private String userSex;//性别
    private int userAge;//年龄
    private String phoneNumber;//用户手机号主键
    private String address;//用户地址
    private String createTime;//用户创建时间

    public User() {
    }

    public User(String userName, String userPwd, String userSex, int userAge, String phoneNumber, String address, String createTime) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userSex = userSex;
        this.userAge = userAge;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    //重写了用户手机号的equals和hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phoneNumber == user.phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
