package com.team.housebackapi.service;

import com.team.housebackapi.entity.Users;

public interface UsersService {

    public int regUser(Users users);//注册

    //登录
    public Users Login(String username,String password);
}
