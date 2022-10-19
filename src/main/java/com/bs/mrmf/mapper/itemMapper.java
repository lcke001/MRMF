package com.bs.mrmf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.mrmf.pojo.item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface itemMapper extends BaseMapper<item> {
    public List<item> getItemByAny(@Param("params") Map map);
}
