package com.maklersoft.springbe.models;


import com.maklersoft.springbe.models.enums.Loyality;
import com.maklersoft.springbe.models.enums.Middleman;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.models.utils.Entity;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.time.LocalDateTime;
import java.util.Date;

@Document(indexName = "ms-persons")
@Setting(settingPath = "normalizer.json")
@Data
public class Person extends Contact implements Project, Entity {
    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String lastName;
    @Field(type = FieldType.Keyword)
    private Middleman middleman;
    @Field(type = FieldType.Keyword)
    private String userRef;
    @Field(type = FieldType.Keyword)
    private Loyality loyalty;
    // @Field(type = FieldType.Keyword)
    // private String agentOrgId;

    @Override
    public void beforeSave(String accountId) throws BeforeSaveException {
        super.beforeSave(accountId);
        this.setChangeDate(LocalDateTime.now());
        this.setQueryField(new StringBuilder()
                .append(this.getLastName()).append(' ')
                .append(this.getName()).append(' ')
                .toString()
        );
    }
}
