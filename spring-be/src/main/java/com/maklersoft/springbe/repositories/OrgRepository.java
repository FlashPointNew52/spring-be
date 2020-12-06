package com.maklersoft.springbe.repositories;

import com.maklersoft.springbe.models.Organisation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrgRepository extends ElasticsearchRepository<Organisation, String> {

}
 
