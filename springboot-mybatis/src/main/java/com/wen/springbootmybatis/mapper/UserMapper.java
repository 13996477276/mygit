package com.wen.springbootmybatis.mapper;

import com.wen.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//这个注解表示了这是一个mybatis的mapper类，跟启动页面加MapperScan到com.wen.springbootmybatis.mapper一样
@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();
    User queryUserById(String id);
    int addUser(User user);
}
