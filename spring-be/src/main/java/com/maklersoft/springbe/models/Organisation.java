package com.maklersoft.springbe.models;

import com.maklersoft.springbe.models.enums.*;
import com.maklersoft.springbe.models.utils.Confirmation;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(indexName = "ms-orgs")
@Setting(settingPath = "normalizer.json")
@Data
public class Organisation extends Contact{
    @Field(type = FieldType.Text, normalizer = "lowercase")
    private String name;

    @Field(type = FieldType.Keyword)
    private Middleman middleman;
    @Field(type = FieldType.Nested)
    private Person contact;
    @Field(type = FieldType.Keyword)
    private GoverType goverType;
    @Field(type = FieldType.Nested)
    private Organisation mainOffice;
    @Field(type = FieldType.Keyword)
    private String orgRef;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<User> agents; //Несколько ответственных у пользователя

    @Field(type = FieldType.Nested)
    private AddressBlock addressBlock;

    @Field(type = FieldType.Nested)
    private Organisation organisation;


    public Organisation() {
        super();
    }
}
