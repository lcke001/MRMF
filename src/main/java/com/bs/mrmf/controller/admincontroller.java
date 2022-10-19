package com.bs.mrmf.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mrmf.pojo.administrator;
import com.bs.mrmf.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/action")
public class admincontroller {

    @Autowired
    adminService as;

    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestBody Map<String,String> parm, HttpSession session){
        Map msg = new HashMap<String,Object>();

        String account = parm.get("account");
        String password = parm.get("password");

        QueryWrapper<administrator> wrapper = new QueryWrapper();
        wrapper.eq("account",account);
        wrapper.eq("password",password);
        administrator one = as.getOne(wrapper);
        if(one != null){
            if(one.getRootTag().equals("1")){
                msg.put("flag",true);
                session.setAttribute("admin",one);
            }else {
                msg.put("flag",false);
                msg.put("tip","账号不可用");
            }

        }else {
            msg.put("flag",false);
            msg.put("tip","账号或密码错误");
        }
        return msg;
    }

    @GetMapping("/exit")
    public String login(HttpSession session){
        session.setAttribute("admin",null);
        return "login";
    }

}
