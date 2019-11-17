package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> findAllUser() {
        List<User> userList = userDao.findAllUser();

        for (User user : userList) {
            Long id = user.getId();
            List<Role> roles = roleDao.findRoleByUserId(id);

            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void addUser(User user, String[] roleIds) {
        Long userId = userDao.addUser(user);

        for (String roleId : roleIds) {
            userDao.addUserAndRole(userId,roleId);
        }
    }

    @Override
    public void delUser(Integer userId) {

        userDao.delUserAndRole(userId);

        userDao.delUser(userId);

    }
}
