package com.maklersoft.springbe.repositories;

import com.maklersoft.springbe.models.Offer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferRepository extends ElasticsearchRepository<Offer, String> {

}
