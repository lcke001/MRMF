package com.bs.mrmf.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bs.mrmf.pojo.administrator;
import com.bs.mrmf.pojo.item;
import com.bs.mrmf.service.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class itemController {
    @Autowired
    itemService is;

    @PostMapping("/addItem")
    @ResponseBody
    public Object addItem(@RequestBody item item, HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        administrator admin = (administrator) request.getSession().getAttribute("admin");
        item.setState("1");
        item.setCreatedate(format);
        item.setRoot(admin.getName());

        System.out.println(item);
        boolean save = is.save(item);

        Map map = new HashMap<String,Object>();
        if (! save){
            map.put("flag",false);
            return map;
        }
        map.put("flag",true);
        return map;
    }

    @PostMapping("/SetItemState")
    @ResponseBody
    public Object setItemState(@RequestBody Map<String,String> parm){
        String id = parm.get("id");
        String recharge = parm.get("recharge");

        Map map = new HashMap<String,Object>();


        UpdateWrapper<item> wrapper = new UpdateWrapper();
        wrapper.eq("id",id);

        item item = is.getById(id);
        if(item.getState().equals("1")){
            item.setState("2");
        }else {
            item.setState("1");
        }

        boolean update = is.update(item, wrapper);

        if (! update){
            map.put("flag",false);
            return map;
        }
        map.put("flag",true);
        return map;
    }

    @PostMapping("/getItemByAny")
    @ResponseBody
    public Object getItemByAny(@RequestBody Map<String,String> parm){
        String price = parm.get("price");
        String name = parm.get("name");
        String date = parm.get("date");

        Map<String,String> map = new HashMap();
        map.put("price",price);
        map.put("name",name);
        map.put("date",date);

        List<item> itemByAny = is.getItemByAny(map);

        return itemByAny;
    }

    @PostMapping("/GetAllItem")
    @ResponseBody
    public Object getAllItem(){

        QueryWrapper<item> wrapper = new QueryWrapper();
        wrapper.eq("state","1");

        List<item> list = is.list(wrapper);

        return list;
    }
}
