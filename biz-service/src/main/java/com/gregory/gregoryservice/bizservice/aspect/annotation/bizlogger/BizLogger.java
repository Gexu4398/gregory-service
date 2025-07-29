package com.gregory.gregoryservice.bizservice.aspect.annotation.bizlogger;

import com.gregory.gregoryservice.bizservice.aspect.annotation.resolver.Resolve;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author singhand
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(BizLoggers.class)
public @interface BizLogger {

  Resolve module();

  String type();

  String contentFormat();

  // 什么是 BeanPath？
  // 参考文献：http://hutool.cn/docs/#/core/JavaBean/%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%A7%A3%E6%9E%90-BeanPath
  Resolve[] contentFormatArguments() default {};

  Resolve targetId();

  Resolve targetName();

  Resolve targetType();

  boolean isLogin() default true;
}
