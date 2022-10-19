package com.bs.mrmf.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.mrmf.mapper.ordersMapper;
import com.bs.mrmf.pojo.administrator;
import com.bs.mrmf.pojo.customer;
import com.bs.mrmf.pojo.item;
import com.bs.mrmf.pojo.orders;
import com.bs.mrmf.properties.staticVar;
import com.bs.mrmf.service.customerService;
import com.bs.mrmf.service.itemService;
import com.bs.mrmf.service.ordersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl extends ServiceImpl<ordersMapper, orders> implements ordersService {

    @Autowired
    itemService is;

    @Autowired
    customerService cs;

    @Autowired
    ordersMapper om;



    @Override
    public orders addOrder(String itemId, String customerId, HttpServletRequest request) {
        orders order = new orders();
        item item = is.getById(itemId);
        customer customer = cs.getById(customerId);
        administrator admin = (administrator)request.getSession().getAttribute("admin");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);

        if(customer.getMoney().doubleValue() < item.getPrice().doubleValue()){
            return null;
        }
        order.setOrderId(System.currentTimeMillis()+"");
        order.setRoot(admin.getName());
        order.setMoney(item.getPrice().doubleValue());
        order.setItem(item.getName());
        order.setCustomer(customer.getName());
        order.setDate(format);

        if (!customer.getPhone().equals(staticVar.SUPER_USER_PHONE)){
            UpdateWrapper<customer> wrapper = new UpdateWrapper();
            wrapper.eq("id",customerId);
            BigDecimal newMoney = customer.getMoney().subtract(item.getPrice());
            customer.setMoney(newMoney);
            BigDecimal add = customer.getExpenditure().add(item.getPrice());
            customer.setExpenditure(add);
            double v = customer.getExpenditure().doubleValue();
            if(499 > v && v > 100){
                customer.setLevel("2");
            }else if(3000 > v && v > 500){
                customer.setLevel("3");
            }else if(10000 > v && v > 3000){
                customer.setLevel("4");
            }else if(v > 10000){
                customer.setLevel("5");
            }
            customer.setLog(format);
            cs.update(customer,wrapper);
        }
        return order;
    }

    @Override
    public List<orders> getOrdersByAny(Map map) {
        List<orders> ordersByAny = om.getOrdersByAny(map);
        return ordersByAny;
    }
}
