package com.bs.mrmf.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.mrmf.pojo.orders;
import com.bs.mrmf.properties.staticVar;
import com.bs.mrmf.service.ordersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    ordersService os;

    @PostMapping("/getOrdersByAny")
    @ResponseBody
    public Object getOrdersByAny(@RequestBody Map<String,String> parm){

        List<orders> ordersByAny = os.getOrdersByAny(parm);

        return ordersByAny;

    }

    @PostMapping("/AddOrders")
    @ResponseBody
    public Object addOrders(@RequestBody Map<String,String> parm, HttpServletRequest request){

        Map map = new HashMap<String,Object>();

        orders order = os.addOrder(parm.get("itemId"), parm.get("customerId"), request);
        if (order == null){
            map.put("flag",false);
            return map;
        }
        boolean save = os.save(order);
        if (!save){
            map.put("flag",false);
            return map;
        }
        map.put("flag",true);
        return map;
    }

    @GetMapping("/GetNewOrders")
    @ResponseBody
    public Object getNewOrders(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        String newDate = sdf.format(date);
        QueryWrapper<orders> wrapper = new QueryWrapper();

        wrapper.le("date",newDate).last("ORDER BY date desc limit 0,"+ staticVar.INDEX_NEW_SHOW_NUM);
        List<orders> list = os.list(wrapper);
        return list;
    }


    @GetMapping("/GetOldDayOrder")
    @ResponseBody
    public Object getOldDayOrder(){

        Date oldDate = new Date(System.currentTimeMillis()-(1000*60*60*24));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();

        String oldDay = sdf.format(oldDate);
        String newDay = sdf.format(newDate);

        QueryWrapper<orders> wrapper = new QueryWrapper();

        wrapper.ge("date",oldDay).lt("date",newDay);

        List<orders> list = os.list(wrapper);
        double money = 0.0;
        for (orders orders : list) {
            money += orders.getMoney().doubleValue();
        }
        Map info = new HashMap();
        info.put("money",money);
        return info;
    }
}
