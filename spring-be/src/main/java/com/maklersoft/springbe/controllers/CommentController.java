package com.maklersoft.springbe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maklersoft.springbe.models.Comment;
import com.maklersoft.springbe.models.Person;
import com.maklersoft.springbe.models.User;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.models.utils.Confirmation;
import com.maklersoft.springbe.repositories.CommentRepository;
import com.maklersoft.springbe.repositories.PersonRepository;
import com.maklersoft.springbe.repositories.UserRepository;
import com.maklersoft.springbe.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/${api.version}/comment")
public class CommentController {

    CommentRepository commentRepository;
    PersonRepository personRepository;
    UserRepository userRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, PersonRepository personRepository,
                             UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list(@AuthenticationPrincipal JwtUser user,
                                  @RequestParam(value = "objId") String objId,
                                  @RequestParam(value = "type") String type
    ) {
        Map<String, Object> res = new HashMap<>();
        Person prn = this.personRepository.findById(objId).get();
        Collection<String> phones = prn.getPhonesWithoutState();
        System.out.println(phones);
        SearchHits<Comment> comments;
        if(type.equals("organisation"))
            comments = commentRepository.findByObjTypeAndPhonesInOrderByAddDate(type, phones);
        else
            comments = commentRepository.findByObjTypeNotAndPhonesInOrderByAddDate("organisation", phones);

        //res.put("hitsCount", tasks.size());
        res.put("list", comments);
        return ok(res);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@AuthenticationPrincipal JwtUser user, @RequestBody Comment comment) {
        Map<String, Object> res = new HashMap<>();
        try {
            if(comment.getAgent() != null && !user.getId().equals(comment.getAgent().getId()) ){
                throw new Exception("Нет доступа");
            } else if(comment.getId() == null){
                comment.setAddDate(LocalDateTime.now());
                comment.setDislikeCount(0);
                comment.setDislikeUsers(new String[]{});
                comment.setLikeCount(0);
                comment.setLikeUsers(new String[]{});
                comment.setAgent(userRepository.findById(user.getId()).get());
            }

            Person prn = this.personRepository.findById(comment.getObjId()).get();
            comment.setPhones(prn.getPhonesWithoutState());
            comment = this.commentRepository.save(comment);
            res.put("comment", comment);
            return ok(res);
        } catch(Exception exp){
            res.put("error", exp);
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        }
    }
}
