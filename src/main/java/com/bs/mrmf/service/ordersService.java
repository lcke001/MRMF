package com.bs.mrmf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.mrmf.pojo.orders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ordersService extends IService<orders> {
    public orders addOrder(String itemId, String customerId, HttpServletRequest request);
    public List<orders> getOrdersByAny(Map map);
}
