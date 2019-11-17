package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUser();

    Long addUser(User user);

    void addUserAndRole(Long userId, String roleId);

    void delUserAndRole(Integer userId);

    void delUser(Integer userId);
}
