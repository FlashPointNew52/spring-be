package com.maklersoft.springbe.controllers;

import com.maklersoft.springbe.models.Offer;
import com.maklersoft.springbe.models.Task;
import com.maklersoft.springbe.models.enums.offer.FilterOfferSource;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.repositories.OfferRepository;
import com.maklersoft.springbe.repositories.TaskRepository;
import com.maklersoft.springbe.repositories.UserRepository;
import com.maklersoft.springbe.security.JwtUser;
import org.apache.lucene.spatial3d.geom.GeoPolygon;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/${api.version}/offer")
public class OfferController {

    final OfferRepository offerRepository;
    final ElasticsearchOperations elasticsearchOperations;
    final ElasticsearchOperations elasticsearchImportOperations;
    final UserRepository userRepository;

    public OfferController(OfferRepository offerRepository, UserRepository userRepository,
                           ElasticsearchOperations elasticsearchOperations, ElasticsearchOperations elasticsearchImportOperations) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.elasticsearchOperations = elasticsearchOperations;
        this.elasticsearchImportOperations = elasticsearchImportOperations;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list(@AuthenticationPrincipal JwtUser user,
                                  @RequestParam(value = "source", required = true) FilterOfferSource source,
                                  @RequestParam(value = "page", required = true) Integer page,
                                  @RequestParam(value = "perPage", required = true) Integer perPage,
                                  @RequestParam(value = "searchQuery") String searchQuery,
                                  @RequestParam(value = "searchArea") GeoPolygon searchArea,
                                  @RequestParam(value = "excludeFilter") Map<String,Object> excludeFilter,
                                  @RequestParam(value = "filter", required = true) Map<String,Object> filter,
                                  @RequestParam(value = "sort") Map<String,Object> sort) {
        Map<String, Object> res = new HashMap<>();


        /*NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery().must())
                .withFilter(QueryBuilders.boolQuery().must(termFilter("id", documentId)))
                .build();
        SearchHits<Offer> sampleEntities = elasticsearchOperations.search(searchQuery, Offer.class);

        List<Task> tasks = offerRepository.findByDateBetweenAndAccountIdAndUserCreatedId(
                dateStart, dateEnd, user.getAccountId(), user.getId());
        res.put("hitsCount", tasks.size());
        res.put("list", tasks);*/
        return ok(res);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> get(@RequestParam(value = "id", required = true) String id ) {
        return ok("");
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestParam(value = "id", required = true) String id ) {
        return ok("");
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@AuthenticationPrincipal JwtUser user, @RequestBody Offer offer) {
        Map<String, Object> res = new HashMap<>();
        try {
            //offer.beforeSave(user.getAccountId());
            /*if(offer.getUserCreated() == null){
                offer.setUserCreated(this.userRepository.findById(user.getId()).get());
            }*/
            offer = this.offerRepository.save(offer);
            res.put("offer", offer);
            return ok(res);
        }catch(Exception exp){
            res.put("error", exp);
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        }
    }
}
