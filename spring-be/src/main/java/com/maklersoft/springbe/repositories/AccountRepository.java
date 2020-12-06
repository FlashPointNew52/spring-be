package com.maklersoft.springbe.repositories;

import com.maklersoft.springbe.models.Account;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AccountRepository extends ElasticsearchRepository<Account, String> {
    List<Account> findByMainUserId(String mainUserId);
}
