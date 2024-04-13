package com.gugu.demo.util.excel.poi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
    
    public String name() default "";
}