package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<User> findAllUser() {
        List<User> userList = template.query("select * from sys_user", new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    @Override
    public Long addUser(final User user) {
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstm = connection.prepareStatement("insert into sys_user values (null,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
                pstm.setObject(1,user.getUsername());
                pstm.setObject(2,user.getEmail());
                pstm.setObject(3,user.getPassword());
                pstm.setObject(4,user.getPhoneNum());

                return pstm;
            }
        };
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        template.update(creator,holder);

        long userId = holder.getKey().longValue();
        return userId;
    }

    @Override
    public void addUserAndRole(Long userId, String roleId) {
        template.update("insert into sys_user_role values (?,?)",userId,roleId);
    }

    @Override
    public void delUserAndRole(Integer userId) {

        template.update("delete from sys_user_role where userId =?",userId);
    }

    @Override
    public void delUser(Integer userId) {

        template.update("delete from sys_user where id =?",userId);
    }
}
