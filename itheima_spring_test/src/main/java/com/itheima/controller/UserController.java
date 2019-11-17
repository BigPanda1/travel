package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list(){

        List<User> userList = userService.findAllUser();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");

        return mv;
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAllRole();
        mv.addObject("roleList",roleList);
        mv.setViewName("user-add");
        return mv;
    }

    @RequestMapping("/addUser")
    public String addUser(User user,String[] roleIds){
        userService.addUser(user,roleIds);

        return "redirect:list";
    }

    @RequestMapping("/del/{userId}")
    public String delUser(@PathVariable("userId") Integer userId){
        userService.delUser(userId);

        return "redirect:/user/list";
    }
}
