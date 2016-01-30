package com.ffyc.server.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ffyc.server.common.FieldDataType;

/**
 * 定义字段文档属性的注解 
 * 可用于生成接口文档
 */
@Target(ElementType.FIELD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDoc
{
    /**
     * 名称（中文名）
     * 
     * @return
     */
    String value();

    /**
     * 描述
     * 
     * @return
     */
    String note() default "";

    /**
     * 数据类型
     * 
     * @return
     */
    String type() default FieldDataType.STRING;

    /**
     * 是否路径参数
     * 
     * @return
     */
    boolean pathVar() default false;

    /**
     * 是否必填
     * 
     * @return
     */
    boolean required() default false;

    /**
     * 长度
     * 
     * @return
     */
    int length() default Integer.MAX_VALUE;

    /**
     * 字段默认值
     * 
     * @return
     */
    String defaultValue();
}
