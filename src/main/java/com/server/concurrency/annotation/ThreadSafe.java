package com.server.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用于 类上
 * @ThreadSafe 表示 该类是线程安全的，只用户本系统标识作用 ，没有实际作用。
 *
 */
@Target(ElementType.TYPE)
@Retention( RetentionPolicy.SOURCE)
public @interface ThreadSafe  {
    String value() default "";
}
