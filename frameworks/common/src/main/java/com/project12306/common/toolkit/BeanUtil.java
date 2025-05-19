package com.project12306.common.toolkit;


import org.mapstruct.Mapper;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class BeanUtil {
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("对象转换失败", e);
        }
    }
}
