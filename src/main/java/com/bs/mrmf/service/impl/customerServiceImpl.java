package com.bs.mrmf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.mrmf.mapper.customerMapper;
import com.bs.mrmf.pojo.customer;
import com.bs.mrmf.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class customerServiceImpl extends ServiceImpl<customerMapper, customer> implements customerService {

    @Autowired
    customerMapper cm;

    @Override
    public List<customer> getCustomerByAny(Map map) {
        List<customer> customerByAny = cm.getCustomerByAny(map);
        return customerByAny;
    }
}
