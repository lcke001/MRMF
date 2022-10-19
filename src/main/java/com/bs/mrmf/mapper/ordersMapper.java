package com.bs.mrmf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mrmf.pojo.orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ordersMapper extends BaseMapper<orders> {
    public List<orders> getOrdersByAny(@Param("params") Map map);
}
