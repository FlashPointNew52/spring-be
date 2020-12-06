package com.maklersoft.springbe.models.utils;

import com.maklersoft.springbe.models.Organisation;
import com.maklersoft.springbe.models.Person;
import lombok.Data;

import java.util.Set;

@Data
public class SourceBlock {
    private Set<Person> person;
    private Set<Organisation> organisation;
}
