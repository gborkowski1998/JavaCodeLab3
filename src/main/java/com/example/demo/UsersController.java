package com.example.demo;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
public class UsersController {

    private static final int PAGE_NUMBER = 1;
    private static final int PAGE_SIZE = 3;

    private final List<UserEntity> users = new LinkedList<>();

    @PostConstruct
    private void Create() {
        users.add(new UserEntity(1, "Karol", "Karol@Karol.pl"));
        users.add(new UserEntity(2, "Grzegorz", "Grzegorz@Grzegorz.pl"));
        users.add(new UserEntity(3, "Tomasz", "Tomasz@Tomasz.pl"));
        users.add(new UserEntity(4, "Anna", "Anna@Anna.pl"));
        users.add(new UserEntity(5, "Mateusz", "Mateusz@Mateusz.pl"));
        users.add(new UserEntity(6, "Michal", "Michal@Michal.pl"));
    }
    @RequestMapping("/users/{id}")
    public UserEntity GetUserById(
            @PathVariable int id
    ) {
        return users.stream()
                .filter(userEntity -> userEntity.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Not found user for given id " + id));
    }
    @GetMapping("/users")
    public UsersResults GetUsers(@RequestParam(name = "page-number", required = false) Integer pageNumberParam,
                           @RequestParam(name = "page-size", required = false) Integer pageSizeParam) {
        int pageNumber = pageNumberParam != null ? pageNumberParam : PAGE_NUMBER;
        int pageSize = pageSizeParam != null ? pageSizeParam : PAGE_SIZE;
        int offset = (pageNumber - 1) * pageSize;
        return new UsersResults(pageNumber, users.size() / pageSize, pageSize, users.size(),
                subList(users, offset, pageSize));
    }
    public static <T> List<T> subList(List<T> it, int offset, int limit) {
        if (it == null) {
            return null;
        }
        if (it.size() <= offset) {
            return Collections.emptyList();
        }
        if (limit < 0) {
            limit = it.size();
        }
        if (offset < 0) {
            offset = 0;
        }
        limit = offset + limit > it.size() ? it.size() - offset : limit;
        return (limit == it.size() && offset == 0) ? it : it.subList(offset, offset + limit);
    }
    @DeleteMapping("/users/rem/{id}")
    public Boolean RemoveUserById(
            @PathVariable Long id
    ) {
        UserEntity userEntity = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Not found user for given id " + id));
        return users.remove(userEntity);
    }
    @PostMapping("/user/create")
    public UserEntity CreateUser(@RequestBody UserEntity userEntity){
        users.add(userEntity);
        return userEntity;
    }
}
