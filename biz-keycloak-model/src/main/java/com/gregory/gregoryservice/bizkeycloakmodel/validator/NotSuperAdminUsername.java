package com.gregory.gregoryservice.bizkeycloakmodel.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotSuperAdminUsernameValidator.class)
@Documented
public @interface NotSuperAdminUsername {

    String message() default "不能对 admin 账号执行该操作！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
