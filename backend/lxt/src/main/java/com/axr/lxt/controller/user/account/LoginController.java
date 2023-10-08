package com.axr.lxt.controller.user.account;

import com.axr.lxt.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


    @PostMapping("/user/account/token/")
    private Map<String, String> getToken(@RequestParam Map<String, String> map) {
//        System.out.println(1);
        String username = map.get("username");
        String password = map.get("password");
//        System.out.println(username + ' ' + password);
//        System.out.println(loginService.getToken(username, password));
        return loginService.getToken(username, password);
    }


}