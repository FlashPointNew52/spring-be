package com.maklersoft.springbe.models;

import com.maklersoft.springbe.models.enums.Source;
import com.maklersoft.springbe.models.enums.Stage;
import com.maklersoft.springbe.models.enums.Tag;
import com.maklersoft.springbe.models.enums.Type;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.models.utils.Confirmation;
import com.maklersoft.springbe.models.utils.Entity;
import com.maklersoft.springbe.models.utils.ResponsibleBlock;
import com.maklersoft.springbe.models.utils.SourceBlock;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class Contact implements Entity{
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String name;

    @Field(type = FieldType.Keyword)
    private String accountId;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String description;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime addDate;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime changeDate;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime assignDate;

    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private LocalDateTime archiveDate;

    @Field(type = FieldType.Nested)
    private AddressBlock addressBlock;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Confirmation> phones;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Confirmation> emails;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<String> sites;

    @Field(type = FieldType.Nested)
    private Set<String> whatsapp;

    @Field(type = FieldType.Nested)
    private Set<String> telegram;

    @Field(type = FieldType.Nested)
    private Set<String> viber;

    @Field(type = FieldType.Nested)
    private Set<String> vk;

    @Field(type = FieldType.Nested)
    private Set<String> ok;

    @Field(type = FieldType.Nested)
    private Set<String> facebook;

    @Field(type = FieldType.Nested)
    private Set<String> instagram;

    @Field(type = FieldType.Nested)
    private Set<String> twitter;

    @Field(type = FieldType.Nested)
    private Set<ResponsibleBlock> responsible;

    @Field(type = FieldType.Nested)
    private Organisation organisation;

    @Field(type = FieldType.Keyword)
    private Type typeCode;

    @Field(type = FieldType.Keyword)
    private Stage stageCode;

    @Field(type = FieldType.Nested)
    private SourceBlock sources;

    @Field(type = FieldType.Keyword)
    private Tag tag;

    @Field(type = FieldType.Keyword)
    private String photo;

    @Field(type = FieldType.Keyword)
    private String photoMini;

    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String queryField;

    Contact(){
        this.phones = new HashSet<>();
        this.emails = new HashSet<>();
        this.sites = new HashSet<>();
    }

    public Set<String> getPhonesWithoutState(){
        Set<String> phones = new HashSet<>();
        for(Confirmation ph : getPhones()){
            phones.add((String)ph.getKey());
        }
        return phones;
    }

    @Override
    public void beforeSave(String accountId) throws BeforeSaveException {
        if(this.accountId == null)
            this.accountId = accountId;
        if (this.getId() == null)
            this.setAddDate(LocalDateTime.now());
        if(!this.getAccountId().equals(accountId))
            throw new BeforeSaveException("Access deny. Wrong account");
    }
}
