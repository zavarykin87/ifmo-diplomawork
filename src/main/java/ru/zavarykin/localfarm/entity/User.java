package ru.zavarykin.localfarm.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class User {

    private int id;
    private String location;
    private String login;
    private String password;
    private String email;

    @Transient
    private String passwordConfirm; // поле для подтверждение пароля при регистрации


}
