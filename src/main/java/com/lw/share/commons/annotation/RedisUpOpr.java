package com.lw.share.commons.annotation;

import personal.enums.RedisKeyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liuchen on 2018/5/28.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisUpOpr {

    RedisKeyEnum key();

}
