package com.team.housebackapi.controller;

import com.team.housebackapi.entity.Users;
import com.team.housebackapi.service.UsersService;
import com.team.housebackapi.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/")
public class UsersController {

    @Autowired
    private UsersService userService;

    @RequestMapping("regUser")
    public BaseResult regUser(Users users){
        int temp = userService.regUser(users);
        if (temp>0){
            return new BaseResult(BaseResult.RESULT_SUCCESS,"");
        }else {
            return new BaseResult(BaseResult.RESULT_FAIL,"出错啦！");
        }
    }

    @RequestMapping("userLogin")
    @CrossOrigin(value = "*",allowCredentials ="true")
    public BaseResult userLogin(String username, String password, HttpSession session){
        Users users = userService.Login(username, password);
        if (users==null){
            return new BaseResult(BaseResult.RESULT_FAIL,"登陆失败");
        }else {
            session.setAttribute("logininfo",users);
            session.setMaxInactiveInterval(6000);//十分钟
            return new BaseResult(BaseResult.RESULT_SUCCESS,"");
        }
    }
}
