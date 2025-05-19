package com.project12306.userservice.controller;

import com.project12306.convention.result.Result;
import com.project12306.userservice.dao.entity.UserDO;
import com.project12306.userservice.dto.resp.UserActualQueryRespDTO;
import com.project12306.userservice.dto.resp.UserQueryRespDTO;
import com.project12306.userservice.service.UserService;
import com.project12306.web.Results;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserService userService;

    private final RedissonClient redisson;
    private final RedissonClient redissonClient;

    /**
     * 测试
     */
    @GetMapping("/test")
    public String test(){
        System.out.println(redissonClient != null);
        return "test";
    }

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/api/user-service/query")
    public Result<UserQueryRespDTO> queryUserInfo(@RequestParam("username") @NotEmpty String userName) {
        return Results.success(userService.queryUserByUsername(userName));
    }

    /**
     * 根据用户名查询用户无脱敏信息
     */
    @GetMapping("/api/user-service/actual/query")
    public Result<UserActualQueryRespDTO> queryActualUserInfo(@RequestParam("username") @NotEmpty String userName) {
        return Results.success(userService.queryActualUserByUsername(userName));
    }

    /**
     * 查询用户名是否存在
     */
//    @GetMapping("/api/user-service/has-username")
//    public Result<Boolean> hasUsername(@RequestParam("username") @NotEmpty String userName) {
//        return Results.success(userService.queryUserIsExist(userName));
//    }
}
