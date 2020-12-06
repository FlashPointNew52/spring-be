package com.maklersoft.springbe.repositories;

import com.maklersoft.springbe.models.Comment;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends ElasticsearchRepository<Comment, String> {
    SearchHits<Comment> findByObjTypeAndPhonesInOrderByAddDate(String objType, Collection<String> phones);

    SearchHits<Comment> findByObjTypeNotAndPhonesInOrderByAddDate(String objType, Collection<String> phones);
}
