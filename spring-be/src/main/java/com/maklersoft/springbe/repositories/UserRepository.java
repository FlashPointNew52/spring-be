package com.maklersoft.springbe.repositories;

import com.maklersoft.springbe.models.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    @Query(
            "                {\"bool\": {\n" +
                    "                    \"must\": [\n" +
                    "                        { \"match\": { \"phones.key\": \"?0\" }}\n" +
                    "                    ]\n" +
                    "                }}")
    List<User> findByPhone(String phone);

    List<User> findByName(String name);
}
