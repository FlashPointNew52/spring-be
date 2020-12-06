package com.maklersoft.springbe.repositories;

import com.maklersoft.springbe.models.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {

}
 
