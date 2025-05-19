package com.project12306.userservice.service;

import com.project12306.userservice.dao.entity.UserDO;
import com.project12306.userservice.dto.resp.UserActualQueryRespDTO;
import com.project12306.userservice.dto.resp.UserQueryRespDTO;
import jakarta.validation.constraints.NotEmpty;

public interface UserService {

    UserQueryRespDTO queryUserByUsername(@NotEmpty String username);

    UserActualQueryRespDTO queryActualUserByUsername(@NotEmpty String username);
}
