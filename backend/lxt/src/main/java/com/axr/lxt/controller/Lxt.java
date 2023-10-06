package com.axr.lxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lxt/")
public class Lxt {
    @RequestMapping("index/")
    public Map<String, String> index() {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "Xinrui");
        map.put("lastName", "An");
        map.put("followerCount", "1314");
        map.put("is_followed", "true");
        return map;
    }
}
