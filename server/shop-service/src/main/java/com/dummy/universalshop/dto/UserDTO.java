package com.dummy.universalshop.dto;

import com.dummy.universalshop.dto.base.BaseDTO;

import java.util.Date;

/**
 * Created by Mateusz on 13.05.2017.
 */
public class UserDTO extends BaseDTO {

    private Long userId;
    private String password;
    private String username;
    private String name;
    private String surname;
    private Date birthYear;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
