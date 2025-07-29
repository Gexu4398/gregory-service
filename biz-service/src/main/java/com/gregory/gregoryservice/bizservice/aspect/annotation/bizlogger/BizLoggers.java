package com.gregory.gregoryservice.bizservice.aspect.annotation.bizlogger;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface BizLoggers {

  BizLogger[] value() default {};
}
