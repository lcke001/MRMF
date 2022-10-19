package com.bs.mrmf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.mrmf.pojo.customer;

import java.util.List;
import java.util.Map;

public interface customerService extends IService<customer> {
    public List<customer> getCustomerByAny(Map map);
}
