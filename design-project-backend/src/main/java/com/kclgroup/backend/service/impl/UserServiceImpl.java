package com.kclgroup.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kclgroup.backend.pojo.entity.User;
import com.kclgroup.backend.service.UserService;
import com.kclgroup.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 张小明
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-06-22 21:57:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




