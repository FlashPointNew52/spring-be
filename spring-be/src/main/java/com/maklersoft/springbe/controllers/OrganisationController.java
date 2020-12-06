package com.maklersoft.springbe.controllers;

import com.maklersoft.springbe.models.Organisation;
import com.maklersoft.springbe.models.Person;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.repositories.OrgRepository;
import com.maklersoft.springbe.repositories.PersonRepository;
import com.maklersoft.springbe.repositories.UserRepository;
import com.maklersoft.springbe.security.JwtUser;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/${api.version}/organisation")
public class OrganisationController {

    final OrgRepository orgRepository;
    final UserRepository userRepository;
    final ElasticsearchOperations elasticsearchOperations;

    public OrganisationController(OrgRepository orgRepository, UserRepository userRepository, ElasticsearchOperations elasticsearchOperations) {
        this.orgRepository = orgRepository;
        this.userRepository = userRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list(@AuthenticationPrincipal JwtUser user,
                                  @RequestParam(value = "filter") Map<String,Object> filter,
                                  @RequestParam(value = "query", required = false) String query) {
        Map<String, Object> res = new HashMap<>();
        //Iterable<Person> tasks = personRepository.findAll();
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("accountId", user.getAccountId()))
                /*.withFilter(QueryBuilders.termQuery("accountId", user.getAccountId()))*/
                .build();
        SearchHits<Organisation> orgs = elasticsearchOperations.search(searchQuery, Organisation.class);
        res.put("list", orgs);
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
    public ResponseEntity<?> save(@AuthenticationPrincipal JwtUser user, @RequestBody Organisation org) {
        Map<String, Object> res = new HashMap<>();
        try {
            org.beforeSave(user.getAccountId());
            org = this.orgRepository.save(org);
            res.put("organisation", org);
            return ok(res);
        }catch(BeforeSaveException exp){
            res.put("error", exp);
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        }
    }
}
