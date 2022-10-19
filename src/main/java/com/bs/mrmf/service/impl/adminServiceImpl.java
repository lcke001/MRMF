package com.bs.mrmf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.mrmf.mapper.adminMapper;
import com.bs.mrmf.pojo.administrator;
import com.bs.mrmf.service.adminService;
import org.springframework.stereotype.Service;

@Service
public class adminServiceImpl extends ServiceImpl<adminMapper,administrator> implements adminService {
}
