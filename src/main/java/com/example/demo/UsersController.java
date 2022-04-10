package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostConstruct
    private void onCreate() {
        System.out.println("UsersController::onCreate");
    }

    @PreDestroy
    private void onDestroy() {
        System.out.println("UsersController::onDestroy");
    }

    @RequestMapping("/example1")
    public ResponseEntity<?> example1() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping("/example2")
    public ResponseEntity<?> example2() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @RequestMapping("/api/users")
    @ResponseBody
    public UsersPage getUsers(
            @RequestParam(name = "page-number", defaultValue=1) Integer pageNumber
            @RequestParam(name = "page=sie", defaultValue=20) Integer pageSize
    ){
        return this
    }

    return page;
}

    @RequestMapping(
            value = "/api/user/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public UserEntity createUser(@RequestBody UserEntity user) {
        //TODO: odwołanie do serwisu
        return user;
    }
}