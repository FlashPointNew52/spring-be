package com.maklersoft.springbe.repositories;

import com.maklersoft.springbe.models.Account;
import com.maklersoft.springbe.models.Task;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends ElasticsearchRepository<Task, String> {

    List<Task> findByUserCreated(String userId);

    List<Task> findByDateBetweenAndAccountIdAndUserCreatedId(LocalDateTime start, LocalDateTime end, String accountId, String userCreated);
    List<Task> findByForUsers(String userId);
}
