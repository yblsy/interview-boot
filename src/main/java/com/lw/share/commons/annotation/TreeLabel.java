package com.lw.share.commons.annotation;

import java.lang.annotation.*;

/**
 * @author 刘晨
 * @create 2018-05-20 21:45
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface TreeLabel {

    String id() default "id";

    String parentId() default "parentId";

    String text();
}
