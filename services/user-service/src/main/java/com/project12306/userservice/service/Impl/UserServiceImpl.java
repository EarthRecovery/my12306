package com.project12306.userservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project12306.common.toolkit.BeanUtil;
import com.project12306.userservice.dao.entity.UserDO;
import com.project12306.userservice.dao.mapper.UserMapper;
import com.project12306.userservice.dto.resp.UserActualQueryRespDTO;
import com.project12306.userservice.dto.resp.UserQueryRespDTO;
import com.project12306.userservice.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    @Override
    public UserQueryRespDTO queryUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        if(userDO == null) {
            System.out.println("用户不存在");
            throw new Error("<UNK>");
        }
        return BeanUtil.convert(userDO, UserQueryRespDTO.class);
    }

    @Override
    public UserActualQueryRespDTO queryActualUserByUsername(String username) {
        return BeanUtil.convert(queryUserByUsername(username), UserActualQueryRespDTO.class);
    }
}
