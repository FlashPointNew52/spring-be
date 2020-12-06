package com.maklersoft.springbe.controllers;

import com.maklersoft.springbe.models.Person;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.repositories.PersonRepository;
import com.maklersoft.springbe.repositories.UserRepository;
import com.maklersoft.springbe.security.JwtUser;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
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

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/${api.version}/person")
public class PersonController {

    final PersonRepository personRepository;
    final UserRepository userRepository;
    final ElasticsearchOperations elasticsearchOperations;

    public PersonController(PersonRepository personRepository, UserRepository userRepository, ElasticsearchOperations elasticsearchOperations) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list(@AuthenticationPrincipal JwtUser user,
                                  @RequestParam(value = "filter") Map<String,Object> filter,
                                  @RequestParam(value = "searchQuery", required = false, defaultValue = "") String query) {
        Map<String, Object> res = new HashMap<>();
        //Iterable<Person> tasks = personRepository.findAll();
        BoolQueryBuilder bool = QueryBuilders.boolQuery().must(
                QueryBuilders.matchQuery("accountId", user.getAccountId()));
        if(!query.isBlank()){
            bool.must(QueryBuilders.matchQuery("queryField", query).operator(Operator.AND));
            bool.should(QueryBuilders.matchQuery("lastName", query).boost(10));
            bool.should(QueryBuilders.matchQuery("name", query).boost(8));
        }
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(bool).build();
        SearchHits<Person> pers = elasticsearchOperations.search(searchQuery, Person.class);
        res.put("list", pers);
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
    public ResponseEntity<?> save(@AuthenticationPrincipal JwtUser user, @RequestBody Person person) {
        Map<String, Object> res = new HashMap<>();
        try {
            person.beforeSave(user.getAccountId());
            person = this.personRepository.save(person);
            res.put("person", person);
            return ok(res);
        }catch(BeforeSaveException exp){
            res.put("error", exp);
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        }
    }
}
