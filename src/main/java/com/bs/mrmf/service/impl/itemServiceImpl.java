package com.bs.mrmf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bs.mrmf.mapper.itemMapper;
import com.bs.mrmf.pojo.item;
import com.bs.mrmf.service.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class itemServiceImpl extends ServiceImpl<itemMapper, item> implements itemService {
    @Autowired
    itemMapper im;

    @Override
    public List<item> getItemByAny(Map map) {
        List<item> itemByAny = im.getItemByAny(map);
        return itemByAny;
    }
}
