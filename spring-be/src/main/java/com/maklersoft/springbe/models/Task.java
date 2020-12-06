package com.maklersoft.springbe.models;

import com.maklersoft.springbe.models.enums.*;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.models.utils.Entity;
import com.maklersoft.springbe.models.utils.TaskSteps;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(indexName = "ms-tasks")
@Setting(settingPath = "normalizer.json")
@Data
public class Task implements Entity {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;                      //Идентификатор

    @Field(type = FieldType.Keyword)
    private String accountId;                //Идентификатор аккаунта

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime addDate;               //Дата добавления

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime date;                  //Дата начала

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime endDate;               //Дата окончания

    @Field(type = FieldType.Boolean)
    private Boolean complete;           //завершена

    @Field(type = FieldType.Boolean)
    private Boolean allDay;             //Весь день

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String description;         //Описание

    @Field(type = FieldType.Keyword)
    private Action action;              //Действие

    @Field(type = FieldType.Keyword)
    private Route route;                //Маршрут

    @Field(type = FieldType.Keyword)
    private Priority priority;            //Приоритет

    @Field(type = FieldType.Keyword)
    private Repeate repeat;              //Повторение

    @Field(type = FieldType.Keyword)
    private Integer remind;             //Напоминание

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String note;                //Заметка

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String comment;             //Комментарий

    @Field(type = FieldType.Keyword)
    private Tag tag;                //Тэг

    @Field(type = FieldType.Object)
    private List<TaskSteps> steps;    //Массив шагов

    @Field(type = FieldType.Nested)
    private List<User> forUsers;        //Массив пользователей

    @Field(type = FieldType.Nested)
    private User userCreated;            //Создано пользователем

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String address;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Nested)
    private Project project;

    @Field(type = FieldType.Nested)
    private Task nextTask;

    @Field(type = FieldType.Nested)
    private Task prevTask;

    @Field(type = FieldType.Nested)
    private List<Date> postponed;

    @Override
    public void beforeSave(String accountId) throws BeforeSaveException {
        if(this.accountId == null)
            this.accountId = accountId;
        if(!this.accountId.equals(accountId))
            throw new BeforeSaveException("Access deny. Wrong account");
    }
}
