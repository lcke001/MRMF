package com.bs.mrmf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mrmf.pojo.customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface customerMapper extends BaseMapper<customer> {
    public List<customer> getCustomerByAny(@Param("params") Map map);
}
