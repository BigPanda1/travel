package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole();

    void addRole(Role role);
}
