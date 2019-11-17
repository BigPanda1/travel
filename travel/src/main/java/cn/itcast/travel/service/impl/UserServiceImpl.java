package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao ud = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {

        User u = ud.findByUsername(user.getUsername());

        if (u != null){
            return false;
        }

        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        ud.save(user);

        //邮件内容
        String content ="<a href = 'http://192.168.29.216:8080/travel/user/active?code="+user.getCode()+"'>点击激活黑马旅游网</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");


        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {

        User user = ud.findCode(code);
        if (user != null){
            ud.updateStatus(user);
            return true;
        }else {

        return false;
        }
    }

    @Override
    public User login(User user) {
        User u = ud.findUsernameAndPassword(user.getUsername(),user.getPassword());
        return u;
    }
}
