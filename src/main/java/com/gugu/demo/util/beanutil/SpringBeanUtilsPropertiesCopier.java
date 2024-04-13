package com.gugu.demo.util.beanutil;


import org.springframework.beans.BeanUtils;

/**
 * @author gugu
 * @Classname SpringBeanUtilsPropertiesCopier
 * @Description TODO
 * @Date 2022/7/24 9:24
 */
public class SpringBeanUtilsPropertiesCopier implements PropertiesCopier {

    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanUtils.copyProperties(source, target);
    }
}
