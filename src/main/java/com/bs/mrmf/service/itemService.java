package com.bs.mrmf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.mrmf.pojo.item;

import java.util.List;
import java.util.Map;

public interface itemService extends IService<item> {
    public List<item> getItemByAny(Map map);
}
