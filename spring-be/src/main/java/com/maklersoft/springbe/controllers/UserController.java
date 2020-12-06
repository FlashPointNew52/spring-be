package com.maklersoft.springbe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maklersoft.springbe.models.User;
import com.maklersoft.springbe.models.utils.Confirmation;
import com.maklersoft.springbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/api")
    public String get() {
        return "session";
    }

    @GetMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestParam("lastName") String lastName,
                                        @RequestParam("name") String name,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("password") String password) {
        Map<String, Object> res = new HashMap<>();
        User user = new User();
        user.setLastName(lastName);
        user.setName(name);
        user.setPassword(password);
        user.getPhones().add(new Confirmation(phone, false));
        try {
            String jsonStr = new ObjectMapper().writeValueAsString(user);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        List<User> sameUser = userRepository.findByPhone(phone);
        if (sameUser.size() > 0) {
            System.out.println("same user");
            res.put("user", sameUser.get(0));
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);
        } else {
            user = userRepository.save(user);

            res.put("user", user);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }


    }
}
