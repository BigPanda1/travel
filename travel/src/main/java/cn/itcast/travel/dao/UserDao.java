package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    User findByUsername(String username);

    void save(User user);

    User findCode(String code);

    void updateStatus(User user);

    User findUsernameAndPassword(String username, String password);
}
