package com.maklersoft.springbe.models;

import com.maklersoft.springbe.models.enums.*;
import com.maklersoft.springbe.models.utils.Confirmation;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Document(indexName = "ms-users")
@Setting(settingPath = "normalizer.json")
@Data
public class User extends Contact{
    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String name;

    @Field(type = FieldType.Keyword, normalizer = "lowercase")
    private String lastName;

    @Field(type = FieldType.Keyword)
    private StateUser stateCode;

    private boolean isActive;

    @Field(type = FieldType.Keyword)
    private Position position;

    @Field(type = FieldType.Keyword)
    private Department department;

    @Field(type = FieldType.Keyword)
    private Specialization specialization;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<User> agents; //Несколько ответственных у пользователя

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date lastLoginDate;

    @Field(type = FieldType.Keyword)
    private RoleUser role;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Organisation organisation;

    private String password;
    private String checkCode;

    public User() {
        super();
        isActive = false;
        role = RoleUser.USER;
    }

    public void setPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public User(String name, String lastName, String phone, String email){
        super();
        isActive = false;
        role = RoleUser.USER;
        this.name = name;
        this.lastName = lastName;
        this.getPhones().add(new Confirmation(phone, false));
        this.getEmails().add(new Confirmation(email, false));
    }

}
