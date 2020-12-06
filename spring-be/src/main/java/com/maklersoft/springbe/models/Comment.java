package com.maklersoft.springbe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Document(indexName = "ms-comments", createIndex = true)
@Setting(settingPath = "normalizer.json")
public class Comment {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;                //	Идентификатор комментария

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String text;            //Текст комментария

    @Field(type = FieldType.Nested)
    private Set<String> phones;        //Телефон контакта

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime addDate;          //Дата добавления комментария

    @Field(type = FieldType.Integer)
    private Integer likeCount;     //Количество лайков

    @Field(type = FieldType.Nested)
    private String[] likeUsers;      //Массив ID пользователей с лайками

    @Field(type = FieldType.Integer)
    private Integer dislikeCount;  //Количество дизлайков

    @Field(type = FieldType.Nested)
    private String[] dislikeUsers;   //Массив ID пользователей с дизлайками

    @Field(type = FieldType.Nested)
    private User agent;           //Идентификатор пользователя, который оставил комментарий

    @Field(type = FieldType.Keyword)
    private String objId;                 //ID контакта, используется для поиска дублей

    @Field(type = FieldType.Keyword)
    private String objType;            //ID контакта, используется для поиска дублей
}
