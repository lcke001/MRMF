package com.bs.mrmf.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bs.mrmf.pojo.administrator;
import com.bs.mrmf.pojo.customer;
import com.bs.mrmf.pojo.orders;
import com.bs.mrmf.properties.staticVar;
import com.bs.mrmf.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class customerController {
    @Autowired
    customerService cs;

    @PostMapping("/delVip")
    @ResponseBody
    public Object delVip(@RequestBody Map<String,String> parm){
        String id = parm.get("id");
        Map map = new HashMap<String,Object>();

        if (cs.getById(id).getPhone().equals(staticVar.SUPER_USER_PHONE)) {
            map.put("flag",false);
            return map;
        }

        boolean remove = cs.removeById(id);


        if (! remove){
            map.put("flag",false);
            return map;
        }
        map.put("flag",true);
        return map;
    }

    @PostMapping("/addVip")
    @ResponseBody
    public Object addVip(@RequestBody customer customer,HttpServletRequest request){

        administrator admin = (administrator) request.getSession().getAttribute("admin");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        Map map = new HashMap<String,Object>();

        String vipid = System.currentTimeMillis()+"";

        customer.setId(vipid);
        customer.setLevel("1");
        customer.setExpenditure(BigDecimal.valueOf(0));
        customer.setLog(format);
        customer.setCreatedDate(format);
        customer.setState("1");
        customer.setRoot(admin.getName());

        boolean save = cs.save(customer);


        if (! save){
            map.put("flag",false);
            return map;
        }
        map.put("flag",true);
        return map;
    }

    @PostMapping("/addMoney")
    @ResponseBody
    public Object addMoney(@RequestBody Map<String,String> parm){
        String id = parm.get("id");
        String recharge = parm.get("recharge");

        Map map = new HashMap<String,Object>();


        UpdateWrapper<customer> wrapper = new UpdateWrapper();
        wrapper.eq("id",id);

        QueryWrapper<customer> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);

        customer one = cs.getOne(queryWrapper);
        double v = Double.parseDouble(recharge);
        one.setMoney(BigDecimal.valueOf(v).add(one.getMoney()));
        boolean update = cs.update(one, wrapper);

        if (! update){
            map.put("flag",false);
            return map;
        }
        map.put("flag",true);
        return map;
    }

    @PostMapping("/getOneVip")
    @ResponseBody
    public Object getOneVip(@RequestBody Map<String,String> parm){
        String id = parm.get("id");

        customer customer = cs.getById(id);

        return customer;
    }

    @PostMapping("/getCustomerByAny")
    @ResponseBody
    public Object getCustomerByAny(@RequestBody Map<String,String> parm){
        String phone = parm.get("phone");
        String name = parm.get("name");
        String date = parm.get("date");

        Map<String,String> map = new HashMap();
        map.put("phone",phone);
        map.put("name",name);
        map.put("date",date);

        List<customer> list = cs.getCustomerByAny(map);

        return list;
    }


    @PostMapping("/GetCustomer")
    @ResponseBody
    public Object getNewOrders(@RequestBody Map<String,String> parm){
        String phone = parm.get("phone");

        QueryWrapper<customer> wrapper = new QueryWrapper();

        wrapper.likeLeft("phone",phone);
        List<customer> list = cs.list(wrapper);

        return list;
    }
    @PostMapping("/GetCustomerAllInfo")
    @ResponseBody
    public Object getCustomerAllInfo(@RequestBody Map<String,String> parm){
        String id = parm.get("id");

        QueryWrapper<customer> wrapper = new QueryWrapper();

        wrapper.eq("id",id);
        customer one = cs.getOne(wrapper);
        return one;
    }

}
