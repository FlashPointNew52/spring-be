package com.maklersoft.springbe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maklersoft.springbe.models.Task;
import com.maklersoft.springbe.models.User;
import com.maklersoft.springbe.models.exceptions.BeforeSaveException;
import com.maklersoft.springbe.models.utils.Confirmation;
import com.maklersoft.springbe.models.utils.Utils;
import com.maklersoft.springbe.repositories.TaskRepository;
import com.maklersoft.springbe.repositories.UserRepository;
import com.maklersoft.springbe.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/${api.version}/task")
public class TaskController {

    final TaskRepository taskRepository;
    final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list(@AuthenticationPrincipal JwtUser user,
                                  @RequestParam(value = "dateStart", required = true)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
                                  @RequestParam(value = "dateEnd", required = true)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd,
                                  @RequestParam(value = "filter", required = true) Map<String,Object> filter) {
        Map<String, Object> res = new HashMap<>();
        List<Task> tasks = taskRepository.findByDateBetweenAndAccountIdAndUserCreatedId(
                dateStart, dateEnd, user.getAccountId(), user.getId());
        res.put("hitsCount", tasks.size());
        res.put("list", tasks);
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
    public ResponseEntity<?> save(@AuthenticationPrincipal JwtUser user, @RequestBody Task task) {
        Map<String, Object> res = new HashMap<>();
        try {
            task.beforeSave(user.getAccountId());
            if(task.getUserCreated() == null){
                task.setUserCreated(this.userRepository.findById(user.getId()).get());
            }
            task = this.taskRepository.save(task);
            res.put("task", task);
            return ok(res);
        }catch(BeforeSaveException exp){
            res.put("error", exp);
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        }
    }
}
