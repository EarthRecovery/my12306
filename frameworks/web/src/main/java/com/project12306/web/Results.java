package com.project12306.web;

import com.project12306.convention.result.Result;

public class Results {

    /**
     * 构造成功响应方法，但是结构体为空
     * @return
     */
    public static Result<Void> success(){
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>()
                .setCode(Result.SUCCESS_CODE)
                .setData(data);
    }
}
