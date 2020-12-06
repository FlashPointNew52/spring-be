package com.maklersoft.springbe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

@Document(indexName = "ms-accounts")
@Data
public class Account{

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String mainUserId;

    @Field(type = FieldType.Long)
    private Integer balance;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date createDate;

    @Field(type = FieldType.Keyword)
    private boolean isActive;

    public Account(){
        this.balance = 0;
        this.createDate = new Date();
    }

    public Account(User user){
        this.mainUserId = user.getId();
        this.createDate = new Date();
        this.balance = 0;
        this.isActive = false;
    }
}
