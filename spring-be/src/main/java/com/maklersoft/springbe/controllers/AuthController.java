package com.maklersoft.springbe.controllers;

import com.maklersoft.springbe.models.Account;
import com.maklersoft.springbe.models.exceptions.AlreadyExistException;
import com.maklersoft.springbe.models.requests.CheckCodeRequest;
import com.maklersoft.springbe.models.requests.RegistrationRequest;
import com.maklersoft.springbe.models.utils.Smsc;
import com.maklersoft.springbe.repositories.AccountRepository;
import com.maklersoft.springbe.security.JwtTokenProvider;
import com.maklersoft.springbe.models.User;
import com.maklersoft.springbe.models.requests.AuthenticationRequest;
import com.maklersoft.springbe.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import com.maklersoft.springbe.security.JwtUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

import static org.springframework.http.ResponseEntity.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    final AuthenticationManager authenticationManager;
    final JwtTokenProvider jwtTokenProvider;
    final UserRepository userRepository;
    final AccountRepository accountRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping(value = "/check")
    public ResponseEntity checkSession(@AuthenticationPrincipal JwtUser user) {
        if(user != null){
            return ok(this.userRepository.findById(user.getId()).get());
        } else {
            return status(HttpStatus.UNAUTHORIZED).body(new JwtException("Сессия пользователя не найдена"));
        }
    }

    @PostMapping(value = "/logout")
    public ResponseEntity checkSession(HttpSession session) {
        session.invalidate();
        /*if(user != null){
            return ok(this.userRepository.findById(user.getId()).get());
        } else {*/
            return ok(session);
        //}
    }

    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        Map<Object, Object> model = new HashMap<>();
        try {
           String phone = data.getPhone();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, data.getPassword()));
            User user = this.userRepository.findByPhone(phone).get(0);
            if(user == null)
                throw new UsernameNotFoundException("Username " + phone + "not found");
            String token = jwtTokenProvider.createToken(phone, new ArrayList<>(){{add("USER");}});
            model.put("phone", phone);
            model.put("token", token);
            model.put("user", user);
            return ok(model);
        } catch (AuthenticationException exp) {
            model.put("error", exp);
            model.put("errorMassage", "Пользователь с таким телефоном не найден");
            return status(HttpStatus.BAD_REQUEST).body(model);
        }
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody RegistrationRequest req) {
        Map<Object, Object> model = new HashMap<>();
        try{
            User user;
            try{
                user = this.userRepository.findByPhone(req.getPhone()).get(0);
                throw new AlreadyExistException("User with " + req.getPhone() + " already exist");
            } catch (IndexOutOfBoundsException ex){
                user = new User(req.getUserName(), req.getUserLastName(), req.getPhone(), req.getEmail());
                user.setCheckCode(generateCode());
                user = this.userRepository.save(user);
                Account acc = new Account(user);
                acc = this.accountRepository.save(acc);
                user.setAccountId(acc.getId());
                user = this.userRepository.save(user);
                sendSms("+7" + req.getPhone(),"ПРоверочный код: " + user.getCheckCode());
                model.put("userId", user.getId());
                return ok(model);
            }
        } catch (AuthenticationException exp){
            model.put("error", exp);
            model.put("errorMassage", "Пользователь уже существует");
            return status(HttpStatus.BAD_REQUEST).body(model);
        }

    }

    @PostMapping(value = "/checkCode")
    public ResponseEntity<?> checkCode(@RequestBody CheckCodeRequest req) {
        Map<Object, Object> model = new HashMap<>();
        try{
            User user = this.userRepository.findById(req.getUserId()).get();
            if(user == null)
                throw new UsernameNotFoundException("Пользователь с таким телефоном не найден");
            if(user.getCheckCode().equals(req.getCheckCode())){
                user.setPassword(req.getPassword());
                user.getPhones().forEach(confirmation -> {
                    if(confirmation.getKey().equals(req.getPhone()))
                        confirmation.setValue(true);
                });
                if(!user.isActive())
                    user.setActive(true);
                user = this.userRepository.save(user);
                Account acc = this.accountRepository.findByMainUserId(user.getId()).get(0);
                if(acc.getMainUserId().equals(user.getId()))
                    acc.setActive(true);
                this.accountRepository.save(acc);
                //Устанавливаем у других пользователей с таким телефоном, его не активным
                List<User> anotherUsers = this.userRepository.findByPhone(req.getPhone());
                for(User usr: anotherUsers) {
                    if(!usr.getId().equals(req.getUserId())){
                        usr.getPhones().forEach(confirmation -> {
                            if(confirmation.getKey().equals(req.getPhone()))
                                confirmation.setValue(false);
                        });
                        this.userRepository.save(usr);
                    }
                }
                model.put("result", "OK");
                return ok(model);
            } else{
                throw new UsernameNotFoundException("Код не верен");
            }

        } catch (AuthenticationException exp){
            model.put("error", exp);
            model.put("errorMassage", exp.getMessage());
            return status(HttpStatus.BAD_REQUEST).body(model);
        }

    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody CheckCodeRequest req) {
        Map<Object, Object> model = new HashMap<>();
        try{
            User user = this.userRepository.findByPhone(req.getPhone()).get(0);
            user.setCheckCode(generateCode());
            user = this.userRepository.save(user);
            sendSms("+7" + req.getPhone(),"Проверочный код: " + user.getCheckCode());
            model.put("userId", user.getId());
            return ok(model);
        } catch (AuthenticationException exp){
            model.put("error", exp);
            model.put("errorMassage", "Пользователь с таким телефоном не найден");
            return status(HttpStatus.BAD_REQUEST).body(model);
        }
    }

    private String generateCode() {
        Random rnd = new Random();
        String code = "";
        for (int i = 0; i < 6; i++)
            code += rnd.nextInt(9);
        return code;
    }

    private boolean sendSms(String phone, String text) {
        Smsc sms = new Smsc();
        try {
            String[] ret = sms.send_sms(phone, text, 0, "", "", 0, "MaklerSoft", "");
        } catch (Exception exp) {
            return false;
        }
        return true;
    }
}
