package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @PostConstruct
    private void onCreate() {
        System.out.println("UsersService::onCreate");
    }

    @PreDestroy
    private void onDestroy() {
        System.out.println("UsersService::onDestroy");
    }

    public UsersPage getUsers(int pageNumber, int pageSize)
    {
        List<UserEntity> users = new ArrayList<>();
        users.Add(new UserEntity(1, "test"))


    }
}
