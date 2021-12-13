package com.wen.springbootmybatis.controller;

import com.wen.springbootmybatis.mapper.UserMapper;
import com.wen.springbootmybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MybatisController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("queryUserList")
    public List<User> queryUserList(){
        List<User> userList =  userMapper.queryUserList();
        return userList;
    }

    @GetMapping("addUser")
    public int addUser(){
        int result =  userMapper.addUser(new User("99886","test17827","1290144","90747644"));
        return result;
    }

    @GetMapping("queryUserById")
    public User queryUserById(){
        String id = "0112553403745428623";
        User user =  userMapper.queryUserById(id);
        return user;
    }
}
