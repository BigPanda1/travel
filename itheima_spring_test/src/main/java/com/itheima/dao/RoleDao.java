package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAllRole();

    void addRole(Role role);


    List<Role> findRoleByUserId(Long id);
}
