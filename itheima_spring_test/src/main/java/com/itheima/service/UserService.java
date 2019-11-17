package com.itheima.service;

import com.itheima.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    void addUser(User user, String[] roleIds);

    void delUser(Integer userId);
}
